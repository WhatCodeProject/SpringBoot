package whatcode.study.whatcode.domain.memberTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.MemberTeamSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindResponseDto;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberTeamService {
    private final MemberTeamRepository memberTeamRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    /* member의 team가입*/
    public Long save(MemberTeamSaveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMember_id())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다"));
        Team team = teamRepository.findById(requestDto.getTeam_id())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 팀이 없습니다"));
        return memberTeamRepository.save(MemberTeam.createMemberTeam(member, team)).getId();
    }

    /* member의 id로 팀목록조회*/
    public List<TeamFindResponseDto> findTeamsByMember(TeamFindRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMember_id())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다"));

        List<MemberTeam> memberTeams = memberTeamRepository.findByMemberId(member.getId());
        List<Team> teams = memberTeams.stream().map(MemberTeam::getTeam).collect(Collectors.toList());
        List<TeamFindResponseDto> resDtos = new ArrayList<>();
        for (Team team : teams) {
            resDtos.add(TeamFindResponseDto.builder()
                    .id(team.getId())
                    .teamName(team.getTeamName())
                    .teamType(team.getTeamType())
                    .build()
            );
        }
        return resDtos;
    }
}
