package gimeast.project01.board.repository;

import gimeast.project01.board.dto.BoardListDTO;
import gimeast.project01.board.dto.BoardWithReplyDTO;
import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.User;
import gimeast.project01.common.PageRequestDTO;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("게시물 작성")
    void createBoard() {
        //given
        User findUser = userRepository.findByUserId("test").stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        for (int i = 0; i < 10; i++) {
        Board board = Board.builder()
                .title("테스트 게시물2")
                .content("테스트중2...")
                .user(findUser)
                .build();

        //when
            boardRepository.save(board);
        }

    }

    @Test
    @DisplayName("지연로딩 예외 발생")
    void lazyFetchException() {
        //given
        Optional<Board> fetchedBoard = boardRepository.findById(2L);

        //when
        Board board = fetchedBoard.orElseThrow();
        System.out.println("board = " + board);

        //then
        assertThrows(LazyInitializationException.class, () -> {
            System.out.println("board.getUser = " + board.getUser());
        });
    }

    @Test
    @DisplayName("지연로딩 트랜잭션 사용으로 정상 조회")
    @Transactional
    void lazyFetchTransactionTest() {
        //given
        Optional<Board> fetchedBoard = boardRepository.findById(2L);

        //when
        Board board = fetchedBoard.orElseThrow();
        System.out.println("board = " + board);

        //then
        System.out.println("board.getUser() = " + board.getUser());
        assertNotNull(board.getUser());

        //결과: 조인을 하지 않고 따로 select를 따로한다.
    }

    @Test
    @DisplayName("게시물 페이징 조회 - 작성자와 댓글 수 By Entity")
    @Transactional
    @Rollback(value = false)
    void getBoardWithReplyCount() {
        //given
        PageRequestDTO pageRequestDTO = new PageRequestDTO(1, 10);
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        //when
        Page<Object[]> boardWithReplyCount = boardRepository.findBoardWithReplyCount(pageable);

        //then
        boardWithReplyCount.get().forEach(row -> {
            Board board = (Board)row[0];
            System.out.println(board);
        });
    }

    @Test
    @DisplayName("게시물 페이징 조회 - 작성자와 댓글 수 By DTO")
    void getBoardWithReplyCountByDTO() {
        //given
        PageRequestDTO pageRequestDTO = new PageRequestDTO(1, 10);
        String search = "";

        //when
        Page<BoardListDTO> board = boardRepository.findBoardWithRepliesCount(pageRequestDTO.getPageable(), search);
        board.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("게시물 단건 조회")
    void getBoardWithReply() {
        //given
        Long id = 2L;

        //when
        BoardWithReplyDTO detail = boardRepository.findBoardWithReplies(id);
        System.out.println("detail = " + detail);
    }
}