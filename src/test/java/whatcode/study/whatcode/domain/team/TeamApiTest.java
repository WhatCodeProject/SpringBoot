package whatcode.study.whatcode.domain.team;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.MemberTeamRepository;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
class TeamApiTest {
    private final String port = "8081";

    @Autowired
    EntityManager em;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberTeamRepository memberTeamRepository;

    @After
    public void tearDown() throws Exception{
        teamRepository.deleteAll();
        memberRepository.deleteAll();
        memberTeamRepository.deleteAll();
    }

    @Before
    void settingMember(){
        String memberEmail = "test01@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(memberEmail);
        memberService.save(memberDto);
    }

    @Test
    void teamSave() throws Exception {

        //given
        String memberEmail = "test01@gmail.com";
        String teamName ="A team 01";

        TeamSaveRequestDto teamDto = getTeamSaveRequestDto(memberEmail, teamName);

        //when
        String url = "http://localhost:" + port + "/api/team/save";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, teamDto, Long.class);

        //than
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Team resTeam = teamRepository.getOne(responseEntity.getBody());
        assertThat(resTeam.getTeamName())
                .isEqualTo(teamName);

    }
    private MemberSaveRequestDto getMemberSaveRequestDto(String email) {
        return MemberSaveRequestDto.builder()
                .email(email)
                .password("1234")
                .name("Dexter")
                .nickName("Stranger")
                .build();
    }

    private TeamSaveRequestDto getTeamSaveRequestDto(String email, String teamName) {
        return TeamSaveRequestDto.builder()
                .memberEmail(email)
                .teamName(teamName)
                .teamType(TeamType.WORK)
                .build();
    }

}
