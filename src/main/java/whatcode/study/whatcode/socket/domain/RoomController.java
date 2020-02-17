package whatcode.study.whatcode.socket.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@RestController
public class RoomController {

    private final RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity findAllRoom(){
        List<Room> roomList = roomRepository.findAll();
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInRoom(@PathVariable String id){
        Room room = roomRepository.findById(id);
        return ResponseEntity.ok(room);
    }

}
