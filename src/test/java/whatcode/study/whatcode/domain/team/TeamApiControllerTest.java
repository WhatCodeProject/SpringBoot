package whatcode.study.whatcode.domain.team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamRepository;
import whatcode.study.whatcode.domain.team.team.TeamType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @After
    public void tearDown() throws Exception{
        teamRepository.deleteAll();
    }

    /*
    * 팀등록 테스트
    * input  : memberEmail, teamName, teamType (전부 String)
    * output : ResponseEntity
    * */
    @Test
    public void reg_team_test(){
        String memberEmail1 = "sm01270913@gmail.com";
        String memberName1  = "강성묵";
        String memberPassword1 = "1234";
        String memberNickName1 = "mooogi";
        memberRepository.save(Member.builder().email(memberEmail1).name(memberName1).password(memberPassword1).nickName(memberNickName1).build());

        String inMemberEmail1 = "";
        String inTeamName1    = "whatcode1";
        String inTeamType1     = "WORK";

        TeamSaveRequestDto requestDto = TeamSaveRequestDto.builder()
                .memberEmail(inMemberEmail1)
                .teamName(inTeamName1)
                .teamType(inTeamType1)
                .build();

        String url = "http://localhost:" + port + "/api/team/save";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Team> all = teamRepository.findAll();
        assertThat(all.get(0).getTeamName()).isEqualTo("whatcode1");

    }
}
