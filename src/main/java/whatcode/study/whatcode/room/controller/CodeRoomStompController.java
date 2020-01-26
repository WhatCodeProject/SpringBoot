package whatcode.study.whatcode.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.room.model.CodeMessage;

@RestController
@RequiredArgsConstructor
public class CodeRoomStompController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/code/join")
    public void join(CodeMessage codeMessage) {
        codeMessage.setMessage(codeMessage.getAuthor() + "님이 입장하였습니다.");
        sendMessage(codeMessage);
    }

    @MessageMapping("/code/share")
    public void share(CodeMessage codeMessage) {
        sendMessage(codeMessage);
    }

    private void sendMessage(CodeMessage codeMessage) {
        simpMessagingTemplate
                .convertAndSend("/subscribe/code/room/" + codeMessage.getRoomId(), codeMessage);
    }

}
