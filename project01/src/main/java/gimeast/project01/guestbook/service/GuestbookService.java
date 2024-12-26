package gimeast.project01.guestbook.service;

import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import gimeast.project01.guestbook.mapper.GuestbookMapper;
import gimeast.project01.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository repository;

    public Page<GuestbookDTO> getGuestbooks(PageRequestDTO pageRequest, String search) {
        return repository.findGuestbooksByCondition(pageRequest.getPageable(), search);
    }

    @Transactional
    public Long saveGuestbook(GuestbookDTO dto) {
        Long id = dto.getId();

        if (id != null) {
            Guestbook guestbook = repository.findById(id).orElseThrow();
            guestbook.changeTitleWithContent(dto.getTitle(), dto.getContent());
            return guestbook.getId();
        } else {
            Guestbook guestbook = GuestbookMapper.INSTANCE.dtoToEntity(dto);
            repository.save(guestbook);
            return guestbook.getId();
        }

    }

    public GuestbookDTO getGuestbook(Long id) {
        return repository.findGuestbookDTOById(id);
    }
}
