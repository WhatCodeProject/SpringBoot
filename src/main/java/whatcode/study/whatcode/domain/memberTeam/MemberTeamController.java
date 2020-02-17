package whatcode.study.whatcode.domain.memberTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;

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
