package whatcode.study.whatcode.room;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CodeRoom {

    private String roomId;
    private String roomName;


    public static CodeRoom createCodeRoom(String name) {
        CodeRoom codeRoom = new CodeRoom();
        codeRoom.roomId = UUID.randomUUID().toString();
        codeRoom.roomName = name;
        return codeRoom;
    }

}
