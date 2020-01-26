package whatcode.study.whatcode.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.room.CodeRoom;
import whatcode.study.whatcode.room.CodeRoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class CodeRoomController {

    private final CodeRoomRepository codeRoomRepository;

    @GetMapping
    public ResponseEntity findAllCodeRooms(){
        List<CodeRoom> codeRooms = codeRoomRepository.findAll();
        return ResponseEntity.ok(codeRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInRoom(@PathVariable String id){
        CodeRoom codeRoom = codeRoomRepository.findById(id);
        return ResponseEntity.ok(codeRoom);
    }

}
