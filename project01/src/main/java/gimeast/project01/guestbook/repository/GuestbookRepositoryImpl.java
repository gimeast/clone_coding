package gimeast.project01.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.dto.QGuestbookDTO;
import gimeast.project01.guestbook.entity.QGuestbook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GuestbookRepositoryImpl implements GuestbookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GuestbookDTO> findGuestbooksByCondition(Pageable pageable, String search) {
        QGuestbook qGuestbook = QGuestbook.guestbook;

        // 검색 조건 빌드
        BooleanBuilder builder = new BooleanBuilder();
        if (search != null && !search.isEmpty()) {
            builder.or(qGuestbook.title.contains(search));
            builder.or(qGuestbook.content.contains(search));
        }

        // QueryDSL로 조회
        List<GuestbookDTO> results = queryFactory
                .select(new QGuestbookDTO(qGuestbook.id, qGuestbook.title, qGuestbook.content, qGuestbook.writer))
                .from(qGuestbook)
                .where(builder)
                .orderBy(qGuestbook.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Optional<Long> totalCount = Optional.ofNullable(queryFactory
                .select(qGuestbook.count())
                .from(qGuestbook)
                .where(builder)
                .fetchOne());

        return new PageImpl<>(results, pageable, totalCount.orElse(0L));
    }
}