package whatcode.study.whatcode.domain.memberTeam.memberTeam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.team.team.Team;

import java.util.List;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long>{
    public List<MemberTeam> findByMember(Member member);
}
