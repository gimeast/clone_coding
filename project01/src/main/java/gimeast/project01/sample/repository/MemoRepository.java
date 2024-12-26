package gimeast.project01.sample.repository;

import gimeast.project01.sample.dto.MemoDTO;
import gimeast.project01.sample.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findMemoById(Long id);

    @Query(value = "select m from Memo m where m.id > :id",
    countQuery = "select (m.id) from Memo m where m.id > :id")
    Page<Memo> findMemoQueryPaging(Long id, Pageable pageable);

    @Query(value = "select m.id, m.memoText, CURRENT_DATE from Memo m where m.id > :id",
            countQuery = "select count(m.id) from Memo m where m.id > :id")
    Page<Object[]> findMemoQueryObjectPaging(Long id, Pageable pageable);

    @Query(value = "select *, now() from memo where id > :id",
            countQuery = "select count(*) from memo where id > :id", nativeQuery = true)
    Page<Object[]> findMemoNativeQueryObjectPaging(Long id, Pageable pageable);

    @Query(value = "select new gimeast.project01.sample.dto.MemoDTO(m.id, m.memoText, CURRENT_DATE) from Memo m where m.id > :id",
            countQuery = "select count(m.id) from Memo m where m.id > :id")
    Page<MemoDTO> findMemoQueryDTOPaging(Long id, Pageable pageable);

    @Query(value = "select new gimeast.project01.sample.dto.MemoDTO(m.id, m.memoText, CURRENT_DATE) from Memo m",
            countQuery = "select count(m.id) from Memo m")
    Page<MemoDTO> findMemoQueryDTOPaging2(Pageable pageable);
}
