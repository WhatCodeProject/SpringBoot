package whatcode.study.whatcode.domain.memberTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberTeamService {
    private final MemberTeamRepository memberTeamRepository;
    private final MemberRepository memberRepository;

    public List<MemberTeam> findTeamsByMember(TeamFindRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail());
        return memberTeamRepository.findByMember(member);
    }
}
