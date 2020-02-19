package whatcode.study.whatcode.domain.memberTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamService;
import whatcode.study.whatcode.domain.team.TeamType;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTeamRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    MemberTeamRepository memberTeamRepository;

    @Test
    void findTeamByMemberId() throws Exception {
        // given
        String email = "adad@naver.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(email);

        Long savedMemberId = memberService.save(memberDto);

        TeamSaveRequestDto teamDto1 = getTeamSaveRequestDto(email, "A Team 1");
        TeamSaveRequestDto teamDto2 = getTeamSaveRequestDto(email, "A Team 2");
        TeamSaveRequestDto teamDto3 = getTeamSaveRequestDto(email, "A Team 3");
        teamService.save(teamDto1);
        teamService.save(teamDto2);
        teamService.save(teamDto3);

        String otherEmail = "qwd@naver.com";
        MemberSaveRequestDto otherMemberDto = getMemberSaveRequestDto(otherEmail);
        memberService.save(otherMemberDto);

        TeamSaveRequestDto otherTeamDto1 = getTeamSaveRequestDto(otherEmail, "B Team 1");
        TeamSaveRequestDto otherTeamDto2 = getTeamSaveRequestDto(otherEmail, "B Team 2");
        TeamSaveRequestDto otherTeamDto3 = getTeamSaveRequestDto(otherEmail, "B Team 3");
        teamService.save(otherTeamDto1);
        teamService.save(otherTeamDto2);
        teamService.save(otherTeamDto3);

        em.flush();
        em.clear();
        //when
        List<Team> teamByMemberId = memberTeamRepository.findByMemberId(savedMemberId)
                .stream().map(MemberTeam::getTeam).collect(Collectors.toList());

        //then
        assertThat(teamByMemberId)
                .extracting("teamName")
                .containsOnly("A Team 1", "A Team 2", "A Team 3");

    }


    @Test
    void findTeamByMemberId_NoneFetch() throws Exception {
        // given
        String email = "adad@naver.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(email);

        Long savedMemberId = memberService.save(memberDto);

        TeamSaveRequestDto teamDto1 = getTeamSaveRequestDto(email, "A Team 1");
        TeamSaveRequestDto teamDto2 = getTeamSaveRequestDto(email, "A Team 2");
        TeamSaveRequestDto teamDto3 = getTeamSaveRequestDto(email, "A Team 3");
        teamService.save(teamDto1);
        teamService.save(teamDto2);
        teamService.save(teamDto3);

        String otherEmail = "qwd@naver.com";
        MemberSaveRequestDto otherMemberDto = getMemberSaveRequestDto(otherEmail);
        memberService.save(otherMemberDto);

        TeamSaveRequestDto otherTeamDto1 = getTeamSaveRequestDto(otherEmail, "B Team 1");
        TeamSaveRequestDto otherTeamDto2 = getTeamSaveRequestDto(otherEmail, "B Team 2");
        TeamSaveRequestDto otherTeamDto3 = getTeamSaveRequestDto(otherEmail, "B Team 3");
        teamService.save(otherTeamDto1);
        teamService.save(otherTeamDto2);
        teamService.save(otherTeamDto3);

        em.flush();
        em.clear();
        //when
        Member member = memberRepository.findById(savedMemberId).get();
        List<Team> teamByMemberId = memberTeamRepository.findByMember(member)
                .stream().map(MemberTeam::getTeam)
                .collect(Collectors.toList());
        //then
        assertThat(teamByMemberId)
                .extracting("teamName")
                .containsOnly("A Team 1", "A Team 2", "A Team 3");

    }

    private MemberSaveRequestDto getMemberSaveRequestDto(String email) {
        return MemberSaveRequestDto.builder()
                .email(email)
                .password("asdad")
                .name("Dexter")
                .nickName("Stranger")
                .build();
    }

    private TeamSaveRequestDto getTeamSaveRequestDto(String email, String title) {
        return TeamSaveRequestDto.builder()
                .memberEmail(email)
                .teamName(title)
                .teamType(TeamType.STUDY)
                .build();
    }

}