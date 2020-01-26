package whatcode.study.whatcode.room.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeMessage {
    private String roomId;
    private String author;
    private String message;
    private MessageType type;
}
