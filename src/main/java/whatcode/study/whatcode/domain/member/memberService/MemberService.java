package whatcode.study.whatcode.domain.member.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatcode.study.whatcode.domain.member.dto.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.member.member.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto) { return memberRepository.save(requestDto.toEntity()).getId(); }
}
