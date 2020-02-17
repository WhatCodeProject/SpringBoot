package whatcode.study.whatcode.domain.team;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamRepository;
import whatcode.study.whatcode.domain.team.team.TeamType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup(){
        teamRepository.deleteAll();
    }

    @Test
    public void regTeam_Call(){
        String memberEmail1 = "sm01270913@gmail.com";
        String memberEmail2 = "rkd0913@naver.com";
        String teamName1    = "whatcode1";
        String teamName2    = "whatcode2";

        memberRepository.save(Member.builder().email(memberEmail1).name("강성묵").password("1234").nickName("mooogi").build());
        memberRepository.save(Member.builder().email(memberEmail2).name("읭읭이").password("1234").nickName("engeng").build());

        //Member testMember1 = memberRepository.getOne(1L);
        Member testMember1 = Member.builder().email("sm01270913@gmail.com").name("강성묵").nickName("mooogi").password("1234").build();
        System.out.println(testMember1.getName());
        //Member testMember2 = memberRepository.getOne(2L);
        Member testMember2 = Member.builder().email("rkd0913@nate.com").name("읭읭이").nickName("reva").password("1234").build();
        System.out.println(testMember2.getName());

        teamRepository.save(Team.createTeam(teamName1, TeamType.STUDY, testMember1));
        teamRepository.save(Team.createTeam(teamName2, TeamType.STUDY, testMember2));

        List<Team> teamList = teamRepository.findAll();

        //then
        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        assertThat(team1.getMaster().getName()).isEqualTo("강성묵");
        assertThat(team2.getMaster().getName()).isEqualTo("읭읭이");

    }
}
