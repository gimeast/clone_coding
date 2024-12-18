package gimeast.project01.board.repository;

import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        User findUser = userRepository.findByUserId("nomad").stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Board board = Board.builder()
                .title("테스트 게시물2")
                .content("테스트중2...")
                .user(findUser)
                .build();

        //when
        boardRepository.save(board);

        //then
        Board findBoard = boardRepository.findById(board.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        assertNotNull(findBoard);
    }
}