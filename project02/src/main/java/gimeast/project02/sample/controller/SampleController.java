package gimeast.project02.sample.controller;

import gimeast.project02.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    /**
     * 권한 없이 접근 가능
     */
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll....");
    }

    /**
     * 로그인한 사용자만 접근 가능
     */
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("exMember....");
        log.info("authMemberDTO: {}", authMemberDTO);
    }

    /**
     * 관리자 권한만 접근 가능
     */
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin....");
    }
}
