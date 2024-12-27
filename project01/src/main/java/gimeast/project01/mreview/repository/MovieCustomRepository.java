package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.dto.MovieListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieCustomRepository {
    Page<MovieListDTO> findMovieWithGradeAndReviewCount(Pageable pageable, String search);
}
