package whatcode.study.whatcode.domain.member.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.team.team.Team;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    public Member findByEmail(String email);
}
