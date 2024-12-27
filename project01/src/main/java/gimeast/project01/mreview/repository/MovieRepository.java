package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository {
}
