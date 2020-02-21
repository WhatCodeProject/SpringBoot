package whatcode.study.whatcode.domain.team;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.common.ReturnId;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/team/save")
    public ResponseEntity save(@RequestBody TeamSaveRequestDto requestDto) {
        Long result = teamService.save(requestDto);
        if (result > 0L) {
            return ResponseEntity.ok(new ReturnId(result));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
