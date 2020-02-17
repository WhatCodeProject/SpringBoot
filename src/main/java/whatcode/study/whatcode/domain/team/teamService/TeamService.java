package whatcode.study.whatcode.domain.team.teamService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamRepository;
import whatcode.study.whatcode.domain.team.team.TeamType;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(TeamSaveRequestDto requestDto){
        String teamName = requestDto.getTeamName();
        Member master = memberRepository.findByEmail(requestDto.getMemberEmail());

        TeamType teamType;
        if(requestDto.getTeamType().equals("STUDY")){
            teamType = TeamType.STUDY;
        }else if(requestDto.getTeamType().equals("WORK")){
            teamType = TeamType.WORK;
        }else {
            teamType = TeamType.STUDY;
        }

        return teamRepository.save(Team.createTeam(teamName, teamType, master)).getId();
    }

}
