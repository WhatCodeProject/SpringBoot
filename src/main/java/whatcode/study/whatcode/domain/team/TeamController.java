package whatcode.study.whatcode.domain.team;

import lombok.Data;
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
    public ResponseEntity save(@RequestBody TeamSaveRequestDto requestDto) {

        Long result = teamService.save(requestDto);

        if (result > 0) {
            // 이 부분 따로 json설정 안할꺼면 클래스에 감싸서 보내야해요
            // 안그러면 리턴값이 그냥 1 이런식으로 리턴됩니다.
            // 테스트에서 찍히는 출력 비교해서 확인해보세요
//            return new ResponseEntity<>(new Temp(result), HttpStatus.OK);
            return ResponseEntity.ok(new Temp(result));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    static class Temp {
        Long id;

        public Temp(Long id) {
            this.id = id;
        }
    }

}
