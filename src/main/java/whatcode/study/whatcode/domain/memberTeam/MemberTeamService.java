package whatcode.study.whatcode.domain.memberTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.team.Team;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberTeamService {
    private final MemberTeamRepository memberTeamRepository;
    private final MemberRepository memberRepository;

    public List<MemberTeam> findTeamsByMember(TeamFindRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail());

        // TODO 페치조인이라는 건데 쿼리 한 번 확인해보세요
        // 원래 하던 방식으로 하면 쿼리를 매번 조회할 때마다 날리게 됩니다.
        // MemberTeamRepo 테스트있으니깐 확인해보세요
        List<MemberTeam> memberTeams = memberTeamRepository.findByMemberId(member.getId());

        // 얘들을 DTO로 만들어서 리턴해줘야 할거 같네요
        List<Team> teams = memberTeams.stream().map(MemberTeam::getTeam).collect(Collectors.toList());

        return null;
    }
}
