package whatcode.study.whatcode.domain.memberTeam;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamRepository;
import whatcode.study.whatcode.domain.team.TeamService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberTeamApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberTeamRepository memberTeamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @After
    public void tearDown() throws Exception{
        memberTeamRepository.deleteAll();
    }

    /*
    * 팀 조회 테스트
    * input  : memberEmail(String)
    * output : ResponseEntity
    * */
    @Test
    public void reg_team_test(){
        String memberEmail1 = "sm01270913@gmail.com";
        String memberName1  = "강성묵";
        String memberPassword1 = "1234";
        String memberNickName1 = "mooogi";

        String memberEmail2 = "rkd0913@naver.com";
        String memberName2  = "김준수";
        String memberPassword2 = "1234";
        String memberNickName2 = "junsu";

        Member member1 = Member.builder().email(memberEmail1).name(memberName1).password(memberPassword1).nickName(memberNickName1).build();
        Member member2 = Member.builder().email(memberEmail2).name(memberName2).password(memberPassword2).nickName(memberNickName2).build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        String inMemberEmail1 = "sm01270913@gmail.com";
        String inTeamName1    = "whatcode1";
        String inTeamType1     = "WORK";

        String inMemberEmail2 = "sm11111111@gmail.com";
        String inTeamName2    = "whatcode222";
        String inTeamType2    = "STUDY";

        TeamSaveRequestDto requestDto1 = TeamSaveRequestDto.builder()
                .memberEmail(inMemberEmail1)
                .teamName(inTeamName1)
                .teamType(inTeamType1)
                .build();
        TeamSaveRequestDto requestDto2 = TeamSaveRequestDto.builder()
                .memberEmail(inMemberEmail2)
                .teamName(inTeamName2)
                .teamType(inTeamType2)
                .build();

        teamService.save(requestDto1);
        teamService.save(requestDto2);

        List<Team> tlist = teamRepository.findAll();
        System.out.println(tlist.get(0));
        System.out.println(tlist.get(1));
        MemberTeam memberTeam1 = MemberTeam.createMemberTeam(member2,tlist.get(0));
        MemberTeam memberTeam2 = MemberTeam.createMemberTeam(member2,tlist.get(1));
        memberTeamRepository.save(memberTeam1);
        memberTeamRepository.save(memberTeam2);


        String url2 = "http://localhost:" + port + "/api/memberTeam/findTeamsByMember";

        TeamFindRequestDto tfRequestDto = TeamFindRequestDto.builder().email(memberEmail2).build();
        ResponseEntity<Long> responseEntity2 = restTemplate.postForEntity(url2, tfRequestDto, Long.class);

        assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
