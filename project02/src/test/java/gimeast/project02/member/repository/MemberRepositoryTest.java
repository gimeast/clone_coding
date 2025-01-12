package gimeast.project02.member.repository;

import gimeast.project02.member.entity.Member;
import gimeast.project02.member.entity.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("1-80 USER, 81-90 USER/MANAGER, 91-100 ADMIN")
    void insertDummies() {
        //given
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@google.com")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            //when
            if (i <= 80) {
                member.addMemberRole(MemberRole.USER);
            } else if (i <= 90) {
                member.addMemberRole(MemberRole.USER);
                member.addMemberRole(MemberRole.MANAGER);
            } else {
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });

        //then
        List<Member> all = memberRepository.findAll();
        assertThat(all).hasSize(100);
    }

    @Test
    @DisplayName("회원 데이터 조회")
    void getMemberWithRoleSet() {
        //given
        String email = "user81@google.com";
        boolean social = false;

        //when
        Optional<Member> byEmail = memberRepository.findByEmail(email, social);

        //then
        Member member = byEmail.orElseThrow();
        assertThat(member).isNotNull();
        System.out.println("member = " + member);
    }


}