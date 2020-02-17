package whatcode.study.whatcode.domain.team;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/team/save")
    public ResponseEntity save(@RequestBody TeamSaveRequestDto requestDto){

        Long result = teamService.save(requestDto);
        if(result>0){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
