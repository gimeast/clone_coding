package gimeast.project01.mreview.service;

import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.common.UploadResultDTO;
import gimeast.project01.common.utils.FileUtils;
import gimeast.project01.mreview.dto.MovieDTO;
import gimeast.project01.mreview.dto.MovieListDTO;
import gimeast.project01.mreview.dto.ReviewDTO;
import gimeast.project01.mreview.entity.Member;
import gimeast.project01.mreview.entity.Movie;
import gimeast.project01.mreview.entity.MovieImage;
import gimeast.project01.mreview.entity.Review;
import gimeast.project01.mreview.repository.MemberRepository;
import gimeast.project01.mreview.repository.MovieImageRepository;
import gimeast.project01.mreview.repository.MovieRepository;
import gimeast.project01.mreview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final FileUtils fileUtils;

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public boolean saveMovieWithImages(String title, MultipartFile[] uploadFiles) {
        Movie movie = Movie.builder().title(title).build();
        movieRepository.save(movie);

        List<UploadResultDTO> uploadResultDTOList = fileUtils.uploadFiles(uploadFiles);

        for (UploadResultDTO dto : uploadResultDTOList) {
            MovieImage movieImage = MovieImage.builder()
                    .uuid(dto.getUuid())
                    .imgName(dto.getFileName())
                    .path(dto.getFolderPath())
                    .movie(movie)
                    .build();

            movieImageRepository.save(movieImage);
        }

        return movie.getId() != null;
    }

    public Page<MovieListDTO> getMovieList(PageRequestDTO pageRequest, String search) {
        return movieRepository.findMovieWithGradeAndReviewCount(pageRequest.getPageable(), search);
    }

    public MovieDTO getMovieWithAll(Long id) {
        MovieDTO movieDTO = movieRepository.findMovieById(id);

        List<UploadResultDTO> uploadResultDTOList = movieImageRepository.findAllByMovieId(id);
        movieDTO.setUploadResultDTO(uploadResultDTOList);

        return movieDTO;
    }

    @Transactional
    public Long saveReview(ReviewDTO reviewDTO) {
        Movie movie = movieRepository.findById(reviewDTO.getMovieId()).orElseThrow();
        Member member = memberRepository.findByEmail(reviewDTO.getEmail()).orElseThrow();

        Review review = Review.builder()
                .movie(movie)
                .member(member)
                .grade((int) reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();

        reviewRepository.save(review);

        return review.getId();
    }
}
