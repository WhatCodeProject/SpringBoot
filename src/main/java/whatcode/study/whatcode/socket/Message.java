package whatcode.study.whatcode.socket;

import lombok.Getter;
import lombok.Setter;

/* 소켓 통신시 전송되는 Message */
@Getter
@Setter
public class Message {
    private String roomdId;
    private String message;
    private String author;
    private MessageType messageType;
}
