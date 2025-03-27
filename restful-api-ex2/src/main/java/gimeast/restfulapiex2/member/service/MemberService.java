package gimeast.restfulapiex2.member.service;

import gimeast.restfulapiex2.member.dto.MemberDTO;
import gimeast.restfulapiex2.member.entity.MemberEntity;
import gimeast.restfulapiex2.member.exception.MemberExceptions;
import gimeast.restfulapiex2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDTO read(String mid, String mpw) {
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.BAD_CREDENTIALS::get);

        if (!passwordEncoder.matches(mpw, memberEntity.getMpw())) {
            throw MemberExceptions.BAD_CREDENTIALS.get();
        }
        return new MemberDTO(memberEntity);
    }

    public MemberDTO getByMid(String mid) {
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);
        return new MemberDTO(memberEntity);
    }
}
