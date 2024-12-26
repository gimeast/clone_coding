package gimeast.project01.guestbook.repository;

import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook>, GuestbookRepositoryCustom {

    @Query("select new gimeast.project01.guestbook.dto.GuestbookDTO(g.id, g.title, g.content, g.writer) from Guestbook g where g.id=:id")
    GuestbookDTO findGuestbookDTOById(Long id);
}
