package gimeast.project01.mreview.repository;

import gimeast.project01.mreview.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    @DisplayName("더미데이터 생성")
    void createDummyData() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r" + i + "@google.com")
                    .pw("123")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });
    }
    
    @Test
    @DisplayName("사용자 삭제시 리뷰까지 삭제")
    void deleteMemberWithReviews() {
        //given
        Member member = Member.builder().id(1L).build();
        
        //when
        memberRepository.delete(member);

        //then
        Optional<Member> byId = memberRepository.findById(1L);
        if(byId.isPresent()) {
            fail("Member is not deleted");
        }
    }

}