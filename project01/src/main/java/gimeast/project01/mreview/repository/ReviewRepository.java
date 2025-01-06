package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.dto.ReviewDTO;
import gimeast.project01.mreview.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select new gimeast.project01.mreview.dto.ReviewDTO(r.id, r.grade, r.text, r.member.email) from Review r where r.movie.id =:id")
    List<ReviewDTO> findAllByMovieId(Long id);

    @Query("select new gimeast.project01.mreview.dto.ReviewDTO(r.id, r.grade, r.text, r.member.email) from Review r where r.movie.id =:id")
    Page<ReviewDTO> findAllByMovieId(Long id, Pageable pageable);
}
