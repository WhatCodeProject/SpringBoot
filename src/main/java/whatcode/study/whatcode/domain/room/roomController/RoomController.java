package whatcode.study.whatcode.domain.room.roomController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.room.roomService.RoomService;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;
import whatcode.study.whatcode.domain.team.teamService.TeamService;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/api/room/save")
    public ResponseEntity save(@RequestBody RoomSaveRequestDto requestDto){
        Long result = roomService.save(requestDto);
        if(result>0){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST );
        }
    }

}