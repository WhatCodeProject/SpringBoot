package whatcode.study.whatcode.domain.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.memberTeam.MemberTeamRepository;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final MemberTeamRepository memberTeamRepository;

    @Transactional
    public Long save(TeamSaveRequestDto requestDto){
        String teamName = requestDto.getTeamName();
        TeamType teamType = requestDto.getTeamType();
        Member member = memberRepository.findById(requestDto.getMember_id())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자를 찾을수 없습니다"));
        Team savedTeam = teamRepository.save(Team.createTeam(teamName, teamType, member));

        memberTeamRepository.save(MemberTeam.createMemberTeam(member, savedTeam));

        return savedTeam.getId();
    }

}
