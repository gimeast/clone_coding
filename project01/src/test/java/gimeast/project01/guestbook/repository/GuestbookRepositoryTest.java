package gimeast.project01.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import gimeast.project01.common.PageRequestDTO;
import gimeast.project01.guestbook.dto.GuestbookDTO;
import gimeast.project01.guestbook.entity.Guestbook;
import gimeast.project01.guestbook.entity.QGuestbook;
import gimeast.project01.guestbook.mapper.GuestbookMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository repository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    @DisplayName("더미데이터 생성")
    void createDummyData() {
        List<Guestbook> guestbookList = IntStream.rangeClosed(1, 300).mapToObj(i -> Guestbook.builder()
                        .title("title" + i)
                        .content("content" + i)
                        .writer("writer" + i)
                        .build())
                .collect(Collectors.toList());

        repository.saveAll(guestbookList);
    }

    @Test
    @DisplayName("더티체킹 테스트 - 트랜잭션 매니저 사용")
    void updateTest1() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            repository.findById(299L).ifPresent(guestbook -> {
                guestbook.setTitle("update title");
                guestbook.setContent("update content");
            });

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Test
    @DisplayName("더티체킹 테스트 - 트랜잭션 어노테이션 사용")
    @Transactional
    @Commit
    void updateTest2() {
        repository.findById(300L).ifPresent(guestbook -> {
            guestbook.setTitle("update...");
            guestbook.setContent("update...");
        });
    }

    @Test
    @DisplayName("BooleanBuilder 사용1")
    void selectGuestbook1() {
        //given
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String search = "10";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        BooleanExpression exContent = qGuestbook.content.contains(search);
        BooleanExpression exTitle = qGuestbook.title.contains(search);
        BooleanExpression exAll = exContent.or(exTitle);

        BooleanBuilder builder = new BooleanBuilder().and(exAll);

        //when
        Page<Guestbook> guestbooks = repository.findAll(builder, pageable);

        //then
        guestbooks.forEach(System.out::println);
    }

    @Test
    @DisplayName("MapStruct 사용 예제")
    void mapStructExample() {
        GuestbookDTO dto = GuestbookDTO.builder()
                .title("mapStruct title")
                .content("mapStruct content")
                .writer("gimeast")
                .build();

        repository.save(GuestbookMapper.INSTANCE.dtoToEntity(dto));

        repository.findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("Repository에서 DTO로 바로 받아오기")
    void getGuestbookDTOByRepository() {
        Pageable pageable = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build()
                .getPageable(Sort.by("title").descending());
        String search = "테스트";

        Page<GuestbookDTO> page = repository.findGuestbooksByCondition(pageable, search);

        page.forEach(System.out::println);
    }
}
