package whatcode.study.whatcode.domain.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.common.ReturnId;
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.team.TeamController;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/api/room/save")
    public ResponseEntity save(@RequestBody RoomSaveRequestDto requestDto) {
        Long result = roomService.save(requestDto);
        if (result > 0) {
            return ResponseEntity.ok(new ReturnId(result));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
