package gimeast.project01.guestbook.repository;

import gimeast.project01.guestbook.dto.GuestbookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuestbookRepositoryCustom {
    Page<GuestbookDTO> findGuestbooksByCondition(Pageable pageable, String search);
}
