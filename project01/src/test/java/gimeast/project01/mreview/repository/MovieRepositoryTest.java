package gimeast.project01.mreview.repository;

import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.mreview.dto.MovieListDTO;
import gimeast.project01.mreview.entity.Movie;
import gimeast.project01.mreview.entity.MovieImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
class MovieRepositoryTest {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;
    
    @Test
    @DisplayName("더미데이터 생성")
    void createDummyData() {
        IntStream.rangeClosed(1, 37).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie Dummy Data " + i)
                    .build();

            movieRepository.save(movie);

            int randomNum = (int)(Math.random() * 5) +1;

            IntStream.rangeClosed(1, randomNum).forEach(j -> {
                MovieImage movieImg = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                movieImageRepository.save(movieImg);
            });
        });
    }

    @Test
    @DisplayName("영화 목록 조회")
    void getListOfMovie() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(1, 10);
        String search = "";

        Page<MovieListDTO> movieListPage = movieRepository.findMovieWithGradeAndReviewCount(pageRequestDTO.getPageable(), search);
        movieListPage.stream().forEach(System.out::println);
    }
}