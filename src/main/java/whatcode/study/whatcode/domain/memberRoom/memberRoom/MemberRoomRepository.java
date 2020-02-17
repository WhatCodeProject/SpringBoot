package whatcode.study.whatcode.domain.memberRoom.memberRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.team.team.Team;

import java.util.List;

@Repository
public interface MemberRoomRepository extends JpaRepository<MemberRoom,Long>{
    public List<MemberRoom> findByMember(Member member);
}
