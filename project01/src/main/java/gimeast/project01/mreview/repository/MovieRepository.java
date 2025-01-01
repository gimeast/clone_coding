package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.dto.MovieDTO;
import gimeast.project01.mreview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository {

    @Query(value = "select new gimeast.project01.mreview.dto.MovieDTO(m.id, m.title, cast(coalesce(avg(r.grade), 0) as float), cast(coalesce(count(r.id), 0) as int)) from Movie m left join Review r on r.movie = m where m.id =:id group by m.id, m.title")
    MovieDTO findMovieById(Long id);
}
