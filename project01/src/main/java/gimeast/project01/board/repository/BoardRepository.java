package gimeast.project01.board.repository;

import gimeast.project01.board.dto.BoardDTO;
import gimeast.project01.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    @Query("select " +
                "b, " +
                "u, " +
                "count(r) " +
            "from Board b " +
            "left join b.user u " +
            "left join Reply r on r.board = b " +
            "group by b")
    Page<Object[]> findBoardWithReplyCount(Pageable pageable);

    @Query("select new gimeast.project01.board.dto.BoardDTO(b.id, b.title, b.content, b.user.userId) from Board b where b.id = :id")
    BoardDTO findBoardDTOById(Long id);
}
