package whatcode.study.whatcode.domain.code;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatcode.study.whatcode.domain.code.dtos.CodeSaveRequestDto;


@RequiredArgsConstructor
@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Transactional
    public Long save(CodeSaveRequestDto requestDto) {
        Code code = requestDto.toEntity();
        return codeRepository.save(code).getId();
    }
}
