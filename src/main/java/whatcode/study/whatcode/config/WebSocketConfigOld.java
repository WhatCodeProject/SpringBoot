package whatcode.study.whatcode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfigOld implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // topic을 접두사로 가지는 client에게 단순한 메모리기반의 message가 전달된다.
        registry.enableSimpleBroker("/topic");

        // @MessageMapping의 Prefixes를 지정한다.
        // /app/hello로 요청이 오면 GreetingController.greetings이 매핑될 것이다.
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // gs-guide-websocket을 엔드포인트로 등록하면 웹 소켓을 사용할수 없을 때
        // 대체 전송이 가능한 SockJs fallback option을 사용
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOrigins("http://192.168.1.248:8081")
                .withSockJS();

        registry.addEndpoint("/whatCodeTest")
                .setAllowedOrigins("http://localhost:8081")
                .withSockJS();
    }
}
