package gimeast.project01.board.repository;

import gimeast.project01.board.dto.BoardListDTO;
import gimeast.project01.board.dto.BoardWithReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {
    Page<BoardListDTO> findBoardWithRepliesCount(Pageable pageable, String search);
    BoardWithReplyDTO findBoardWithReplies(Long id);
}
