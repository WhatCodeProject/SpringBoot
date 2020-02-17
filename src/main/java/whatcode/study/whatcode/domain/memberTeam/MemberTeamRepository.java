package whatcode.study.whatcode.domain.memberTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.member.Member;

import java.util.List;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long>{
    public List<MemberTeam> findByMember(Member member);
}
