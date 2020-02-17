package whatcode.study.whatcode.socket.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Room {
    String roomId;
    String title;

    public Room(String title) {
        this.title = title;
        roomId = UUID.randomUUID().toString();
    }
}
