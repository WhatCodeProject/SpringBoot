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
        // TODO 이부분은 MemberTeam을 내보낼게 아니라 TeamDto를 내보내야 할거같네요
        // MemberTeamTempRepository 참고하시고
        return memberTeamService.findTeamsByMember(requestDto);
    }

}
