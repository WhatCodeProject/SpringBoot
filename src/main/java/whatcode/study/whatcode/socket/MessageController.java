package whatcode.study.whatcode.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 코드 공유, 채팅기능 컨트롤러
 * <p>
 * - connection이 맺어진 사용자들은 해당 RoomId에 맞는 데이터를 실시간으로 받아올 수 있다.
 *
 * @author Dongmyeong Lee
 */
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    // 방번호에 맞는 유저들을 담는 컬렉션
    private final Map<String, Set<String>> userSetByRoomId = new LinkedHashMap<>();

    // 방번호에 맞는 마지막 코드 내역을 담는 컬렉션
    private final Map<String, String> roomCode = new HashMap<>();

    /**
     * 접속중인 유저
     * - Connection이 맺어진 후 해당 커넥션에 접속중인 유저들에 대한 정보를 RoomId가 Key인 Map에 담아 반환한다.
     */
    @MessageMapping("/room/connect")
    public void connect(Message message) {
        String roomId = message.getRoomId();
        if (!userSetByRoomId.containsKey(roomId)) {
            userSetByRoomId.put(roomId, new LinkedHashSet<>());
        }
        Set<String> userSet = userSetByRoomId.get(roomId);
        userSet.add(message.getAuthor());
        messagingTemplate.convertAndSend("/subscribe/room/" + roomId, new ConnectDto(userSet, roomCode.get(roomId)));
    }

    /**
     * 채팅
     * 1. 룸에 접속한 유저들은 접속 시 이전의 채팅 내역을 받아왔을 것이다.
     * 2. 그 후 유저들이 채팅을 하여 해당 메서드가 핸들링될 때 채팅 데이터를 저장하고 현재 커넥션이 된 사용자들에게는 바로 반환해준다.
     *
     * @param message 의 MesageType은 CHAT이 될 것이다.
     */
    @MessageMapping("/room/chat")
    public void chat(Message message) {

        // TODO: SAVE CHAT DATA

        messagingTemplate.convertAndSend("/subscribe/room/" + message.getRoomId(), message);
    }

    /**
     * 코드 공유
     * - 코드 공유의 경우 코드 저장시에만 코드를 저장하므로 따로 처리할 필요가 없다.
     *
     * @param message 의 MesageType은 CODE가 될 것이다.
     */
    @MessageMapping("/room/code")
    public void codeShare(Message message) {
        String roomId = message.getRoomId();
        roomCode.put(roomId, message.getMessage());
        messagingTemplate.convertAndSend("/subscribe/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/room/disconnect")
    public void disconnect(Message message){
        String roomId = message.getRoomId();
        Set<String> userSet = userSetByRoomId.get(roomId);
        if (userSet != null){
            userSet.remove(message.getAuthor());
        }
        messagingTemplate.convertAndSend("/subscribe/room/" + message.getRoomId(), new ConnectDto(userSet, roomCode.get(roomId)));

    }

}
