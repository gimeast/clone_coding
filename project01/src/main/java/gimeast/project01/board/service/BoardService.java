package gimeast.project01.board.service;

import gimeast.project01.board.dto.BoardDTO;
import gimeast.project01.board.dto.BoardListDTO;
import gimeast.project01.board.dto.BoardWithReplyDTO;
import gimeast.project01.board.dto.ReplyDTO;
import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.Reply;
import gimeast.project01.board.entity.User;
import gimeast.project01.board.mapper.BoardMapper;
import gimeast.project01.board.repository.BoardRepository;
import gimeast.project01.board.repository.ReplyRepository;
import gimeast.project01.board.repository.UserRepository;
import gimeast.project01.common.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    public Page<BoardListDTO> getBoardList(PageRequestDTO pageRequestDTO, String search) {
        return boardRepository.findBoardWithRepliesCount(pageRequestDTO.getPageable(), search);
    }

    public BoardWithReplyDTO getBoardWithReply(Long id) {
        return boardRepository.findBoardWithReplies(id);
    }

    @Transactional
    public Long saveBoard(BoardDTO dto) {

        if (dto.getId() != null) {
            Board board = boardRepository.findById(dto.getId()).orElseThrow();
            board.changeTitleWithContent(dto.getTitle(), dto.getContent());
            return board.getId();
        } else {
            Board board = BoardMapper.INSTANCE.dtoToEntity(dto);
            User user = userRepository.findByUserId(dto.getUserId()).getFirst();
            board.setUser(user);

            boardRepository.save(board);
            return board.getId();
        }

    }

    public BoardDTO getBoard(Long id) {
        return boardRepository.findBoardDTOById(id);
    }

    public Long saveReply(Long boardId, ReplyDTO dto) {
        Reply reply = Reply.builder()
                .text(dto.getText())
                .board(boardRepository.findById(boardId).orElseThrow())
                .user(userRepository.findByUserId(dto.getUserId()).getFirst())
                .build();

        Reply savedReply = replyRepository.save(reply);

        return savedReply.getBoard().getId();
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public List<String> getAllUserIds() {
        return userRepository.findAllUserIds();
    }
}
