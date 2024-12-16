package gimeast.project01.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import gimeast.project01.guestbook.common.PageRequestDTO;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import gimeast.project01.guestbook.entity.QGuestbook;
import gimeast.project01.guestbook.mapper.GuestbookMapper;
import gimeast.project01.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuestbookService {
    private final GuestbookRepository repository;

    /**
     * @params pageRequest
     * @param search
     * @return guestbooks
     */
    public Page<GuestbookDTO> getGuestbooks(PageRequestDTO pageRequest, String search) {
        Pageable pageable = PageRequestDTO.builder()
                .page(pageRequest.getPage())
                .size(pageRequest.getSize())
                .build()
                .getPageable(Sort.by("title").descending());

        return repository.findGuestbooksByCondition(pageable, search);
    }

    @Transactional
    public Long saveGuestbook(GuestbookDTO dto) {
        Long id = dto.getId();

        if (id != null) {
            Guestbook guestbook = repository.findById(id).orElseThrow();
            guestbook.setTitle(dto.getTitle());
            guestbook.setContent(dto.getContent());
            guestbook.setWriter(dto.getWriter());
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
