package whatcode.study.whatcode.domain.member.memberController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatcode.study.whatcode.domain.member.memberService.MemberService;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
}
