package whatcode.study.whatcode.domain.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.code.dtos.CodeSaveRequestDto;
import whatcode.study.whatcode.domain.common.ReturnId;


@RequiredArgsConstructor
@RestController
public class CodeController {
    private final CodeService codeService;

    @PostMapping("/api/code/save")
    public ResponseEntity save(@RequestBody CodeSaveRequestDto requestDto){
        Long savedCodeId = codeService.save(requestDto);
        if(savedCodeId > 0L){
            return ResponseEntity.ok(new ReturnId(savedCodeId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
