package gimeast.project01.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import gimeast.project01.guestbook.common.PageRequestDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import gimeast.project01.guestbook.entity.QGuestbook;
import gimeast.project01.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestbookService {
    private final GuestbookRepository repository;

    /**
     * @params pageRequest
     * @param search
     * @return guestbooks
     */
    public Page<Guestbook> getGuestbooks(PageRequestDTO pageRequest, String search) {
        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanBuilder builder = buildContentOrTitleSearchPredicate(search, qGuestbook);

        Pageable pageable = PageRequestDTO.builder()
                .page(pageRequest.getPage())
                .size(pageRequest.getSize())
                .build()
                .getPageable(Sort.by("title").descending());

        return repository.findAll(builder, pageable);
    }

    private static BooleanBuilder buildContentOrTitleSearchPredicate(String search, QGuestbook qGuestbook) {
        BooleanExpression exContent = qGuestbook.content.contains(search);
        BooleanExpression exTitle = qGuestbook.title.contains(search);
        BooleanExpression exAll = exContent.or(exTitle);

        return new BooleanBuilder().and(exAll);
    }
}
