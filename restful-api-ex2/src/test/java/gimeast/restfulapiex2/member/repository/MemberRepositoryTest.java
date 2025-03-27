package gimeast.restfulapiex2.member.repository;

import gimeast.restfulapiex2.member.entity.MemberEntity;
import gimeast.restfulapiex2.member.exception.MemberExceptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("더미데이터 추가")
    void testInsert() {
        IntStream.rangeClosed(1, 100)
                .mapToObj(i -> MemberEntity.builder()
                        .mid("user" + i)
                        .mpw(passwordEncoder.encode("1111"))
                        .mname("USER" + i)
                        .email("user" + i + "@aaa.com")
                        .role(i <= 80 ? "USER" : "ADMIN")
                        .build())
                .forEach(memberEntity -> memberRepository.save(memberEntity));
    }

    @Test
    void testRead() {
        //given
        String mid = "user1";
        Optional<MemberEntity> result = memberRepository.findById(mid);

        //when
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        //then
        System.out.println("memberEntity = " + memberEntity);
    }

    @Test
    @Transactional
    @Commit
    void testUpdate() {
        //given
        String mid = "user1";

        //when
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        //then
        memberEntity.changePassword(passwordEncoder.encode("2222"));
        memberEntity.changeName("USER1-1");
    }

    @Test
    @Transactional
    @Commit
    void testDelete() {
        //given
        String mid = "user1";

        //when
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        //then
        memberRepository.delete(memberEntity);
    }
}