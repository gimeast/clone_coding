package gimeast.project01.board.repository;

import gimeast.project01.board.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        //given
        User user = User.builder()
                .userId("nomad2")
                .password("123")
                .name("노마드")
                .mobile("01000000000")
                .build();

        //when
        userRepository.save(user);
        
        //then
        User findUser = userRepository.findByUserId("nomad").stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        assertNotNull(findUser);
    }

}