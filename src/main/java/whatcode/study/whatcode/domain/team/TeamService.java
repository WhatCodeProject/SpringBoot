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
        Member member = memberRepository.findByEmail(requestDto.getMemberEmail());
        Team savedTeam = teamRepository.save(Team.createTeam(teamName, teamType, member));


        memberTeamRepository.save(MemberTeam.createMemberTeam(member, savedTeam));


        return savedTeam.getId();
    }

}
