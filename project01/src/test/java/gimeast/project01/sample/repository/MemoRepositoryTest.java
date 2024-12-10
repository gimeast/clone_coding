package gimeast.project01.sample.repository;

import gimeast.project01.sample.dto.MemoDTO;
import gimeast.project01.sample.entity.Memo;
import gimeast.project01.sample.repository.MemoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    @DisplayName("더미 데이터 생성")
    void createDummyData() {
        for (int i = 0; i < 500; i++) {
            memoRepository.save(new Memo("Sample" + i));
        }
    }

    @Test
    @DisplayName("조회 테스트")
    void getMemo() {
        //given
        Long id = 301L;

        //when
        Optional<Memo> memo = memoRepository.findById(id);

        //then
        assertThat(memo).isNotEmpty();
    }

    @Test
    @DisplayName("업데이트 테스트")
    void updateMemo() {
        Memo updateMemo = new Memo(301L, "update text2");
        memoRepository.save(updateMemo);

        Optional<Memo> memo = memoRepository.findById(301L);
        assertThat(memo.get().getMemoText()).isEqualTo("update text2");
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteMemo() {
        for (int i = 0; i < 400; i++) {
            memoRepository.deleteById(i+1L);
        }
    }

    @Test
    @DisplayName("페이징 조회")
    void getMemoPaging() {
        //given
        Sort idSort = Sort.by("id").descending();
        Sort memoTextSort = Sort.by("memoText").ascending();
        Sort sort = idSort.and(memoTextSort);

        Pageable pageable = PageRequest.of(0, 10, sort);

        //when
        Page<Memo> memoResult = memoRepository.findAll(pageable);

        //then
        System.out.println("getTotalPages = " + memoResult.getTotalPages());
        System.out.println("getTotalElements = " + memoResult.getTotalElements());
        System.out.println("getContent = " + memoResult.getContent());
        System.out.println("hasNext = " + memoResult.hasNext());
        System.out.println("hasPrevious = " + memoResult.hasPrevious());
        System.out.println("isFirst = " + memoResult.isFirst());

        List<Memo> content = memoResult.getContent();
        Stream<Memo> memoStream = memoResult.get();

        memoStream.forEach(vo -> {
            System.out.println("vo = " + vo);
        });
    }

    @Test
    @DisplayName("@Query를 이용한 페이징")
    void memoQueryPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> memoPagingQuery = memoRepository.findMemoQueryPaging(450L, pageable);

        List<Memo> content = memoPagingQuery.getContent();
        System.out.println("content = " + content);
    }

    @Test
    @DisplayName("@Query를 이용한 Object[] 페이징")
    void memoQueryObjectPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> memoPagingQuery = memoRepository.findMemoQueryObjectPaging(450L, pageable);

        List<Object[]> content = memoPagingQuery.getContent();
        for (Object[] objects : content) {
            for (Object object : objects) {
                System.out.println("object = " + object);
            }
        }
    }

    @Test
    @DisplayName("@Query를 이용한 Native sql Object[] 페이징")
    void memoNativeQueryObjectPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> memoPagingQuery = memoRepository.findMemoNativeQueryObjectPaging(450L, pageable);

        List<Object[]> content = memoPagingQuery.getContent();

//        for (Object[] objects : content) {
//            for (Object object : objects) {
//                System.out.println("object = " + object);
//            }
//        }

        List<MemoDTO> memoDTOs = content.stream()
                .map(record -> new MemoDTO(
                        ((Long) record[0]),
                        (String) record[1],
                        (Date) record[2]))
                .toList();
        System.out.println("memoDTOs = " + memoDTOs);
    }

    @Test
    @DisplayName("@Query를 이용한 sql DTO 페이징")
    void memoQueryDTOPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MemoDTO> memoPagingQuery = memoRepository.findMemoQueryDTOPaging(450L, pageable);

        for (MemoDTO memoDTO : memoPagingQuery) {
            System.out.println("memoDTO = " + memoDTO);
        }
    }



}