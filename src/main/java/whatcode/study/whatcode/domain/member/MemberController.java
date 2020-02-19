package whatcode.study.whatcode.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginRequestDto;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginResponseDto;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/member/save")
    public ResponseEntity save(@RequestBody MemberSaveRequestDto requestDto) {
        Long savedMemberId = memberService.save(requestDto);
        if (savedMemberId > 0) {
            return ResponseEntity.ok(savedMemberId);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/api/member/login")
    public ResponseEntity login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto memberLoginResponseDto = memberService.login(requestDto);
        if (memberLoginResponseDto != null) {
            return ResponseEntity.ok(memberLoginResponseDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
