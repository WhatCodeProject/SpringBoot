package whatcode.study.whatcode.domain.memberTeam;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.common.ReturnId;
import whatcode.study.whatcode.domain.memberTeam.dtos.MemberTeamSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberTeamController {

    private final MemberTeamService memberTeamService;

    @PostMapping("/api/memberTeam/save")
    public ResponseEntity save(@RequestBody MemberTeamSaveRequestDto requestDto){
        Long savedId = memberTeamService.save(requestDto);
        if (savedId > 0) {
            return ResponseEntity.ok(new ReturnId(savedId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/api/memberTeam/findTeamsByMember")
    public ResponseEntity findTeamsByMember(@RequestBody TeamFindRequestDto requestDto){
        List<TeamFindResponseDto> resDtos = memberTeamService.findTeamsByMember(requestDto);
        if (!resDtos.isEmpty()) {
            return ResponseEntity.ok(resDtos);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
