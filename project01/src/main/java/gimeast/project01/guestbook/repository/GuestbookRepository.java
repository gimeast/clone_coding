package gimeast.project01.guestbook.repository;

import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {

    @Query("SELECT new gimeast.project01.guestbook.dto.GuestbookDTO(g.title, g.content, g.writer) FROM Guestbook g")
    Page<GuestbookDTO> findAllGuestbookDTO(Pageable pageable);
}
