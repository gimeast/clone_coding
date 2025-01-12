package gimeast.project02.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("PasswordEncoder 테스트")
    void passwordEncoderTest() {
        String password = "1111";

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("encodedPassword:" + encodedPassword);

        boolean matchResult = passwordEncoder.matches(password, encodedPassword);
        System.out.println("matchResult = " + matchResult);
//        $2a$10$FJf7PcjIvy5eulqkGOsB3edC9njDF67oajxq3ZTq6aY5gKNLowWs2
//        $2a$10$EXDbDsf2QGmXVQzNAhfoJ.tDOe7an5ZmNq5wL6rkSwgl0nKf03cVe
    }

}
