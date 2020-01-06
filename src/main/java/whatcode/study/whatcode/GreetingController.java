package whatcode.study.whatcode;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    // /hello 요청이 올때 greeting을 호출한다.
    @MessageMapping("/hello")
    @SendTo("/topic/greetings") // return 값이 SendTo에 정의된 리소스로 브로드캐스팅 된다.
    public Greeting greeting(HelloMessage message) throws InterruptedException {

        // 클라이언트가 메시지를 보낸후에 비동기적으로 처리하는데 필요한 시간을 임의로 줬다.
//        Thread.sleep(1000);

        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
    }

}
