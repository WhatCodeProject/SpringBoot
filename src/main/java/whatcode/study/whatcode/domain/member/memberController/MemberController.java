package whatcode.study.whatcode.domain.member.memberController;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginRequestDto;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginResponseDto;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.member.memberService.MemberService;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/member/save")
    public ResponseEntity save(@RequestBody MemberSaveRequestDto requestDto){
        Long result = memberService.save(requestDto);
        if(result>0){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST );
        }
    }
    @PostMapping("/api/member/login")
    public ResponseEntity login(@RequestBody MemberLoginRequestDto requestDto){
        MemberLoginResponseDto memberLoginResponseDto = memberService.login(requestDto);
        if(memberLoginResponseDto != null){
            return new ResponseEntity<>(memberLoginResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST );
        }
    }

}
