package whatcode.study.whatcode.domain.memberTeam.memberTeamController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.memberTeam.memberTeamService.MemberTeamService;
import whatcode.study.whatcode.domain.team.team.Team;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberTeamController {

    private final MemberTeamService memberTeamService;

    @PostMapping("/api/memberTeam/findTeamsByMember")
    public List<MemberTeam> findTeamsByMember(@RequestBody TeamFindRequestDto requestDto){
        return memberTeamService.findTeamsByMember(requestDto);
    }

}
