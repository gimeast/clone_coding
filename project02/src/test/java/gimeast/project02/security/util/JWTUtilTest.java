package gimeast.project02.security.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JWTUtilTest {

    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore() {
        System.out.println("testBefore...");
        jwtUtil = new JWTUtil();
    }

    @Test
    @DisplayName("토큰생성 테스트")
    void createToken() throws Exception {
        //given
        String email = "user95@gmail.com";

        //when
        String token = jwtUtil.generateToken(email);

        //then
        System.out.println("token = " + token);
    }

    @Test
    @DisplayName("검증 테스트")
    void testValidate() throws Exception {
        //given
        String email = "user95@gmail.com";

        //when
        String token = jwtUtil.generateToken(email);
        Thread.sleep(5000);

        String sub = jwtUtil.validateAndExtract(token);

        //then
        System.out.println("sub = " + sub);
    }

}