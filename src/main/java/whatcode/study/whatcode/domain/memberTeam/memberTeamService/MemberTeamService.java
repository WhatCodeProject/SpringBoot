package whatcode.study.whatcode.domain.memberTeam.memberTeamService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeamRepository;
import whatcode.study.whatcode.domain.team.team.Team;

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
