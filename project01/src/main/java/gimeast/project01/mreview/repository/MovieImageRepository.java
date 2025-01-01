package gimeast.project01.mreview.repository;

import gimeast.project01.common.UploadResultDTO;
import gimeast.project01.mreview.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

    @Query("select new gimeast.project01.common.UploadResultDTO(i.imgName, i.uuid, i.path) from MovieImage i where i.movie.id=:id")
    List<UploadResultDTO> findAllByMovieId(Long id);
}
