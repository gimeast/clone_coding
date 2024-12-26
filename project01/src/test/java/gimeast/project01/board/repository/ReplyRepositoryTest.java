package gimeast.project01.board.repository;

import gimeast.project01.board.entity.Board;
import gimeast.project01.board.entity.Reply;
import gimeast.project01.board.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("댓글 작성")
    void createReply() {
        //given
        Board board = boardRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        //when
        User replyer = userRepository.findByUserId("nomad2").stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Reply reply = Reply.builder()
                .text("테스트 중인가요??")
                .board(board)
                .user(replyer)
                .build();

        replyRepository.save(reply);

        //then
        Reply findReply = replyRepository.findById(reply.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));

        assertNotNull(findReply);
    }
    
    @Test
    @DisplayName("연관관계 조회")
    void lazyFetchTest() {
        //given
        Optional<Reply> fetchedReply = replyRepository.findById(1L);

        //when
        Reply reply = fetchedReply.orElseThrow();

        //then
        assertNotNull(reply);
    }
}