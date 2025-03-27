package gimeast.restfulapiex2.member.repository;

import gimeast.restfulapiex2.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
