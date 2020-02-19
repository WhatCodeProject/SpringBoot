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
        Long result = memberService.save(requestDto);
        if (result > 0) {
            // TODO 참고: 이렇게 해도 돼요
            return ResponseEntity.ok(result);
//            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/member/login")
    public ResponseEntity login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto memberLoginResponseDto = memberService.login(requestDto);
        if (memberLoginResponseDto != null) {
            return new ResponseEntity<>(memberLoginResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
