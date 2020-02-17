package whatcode.study.whatcode.domain.member.member;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드. 테스트간 데이터 침범을 막기 위해 사용.
    // 여러 테스트를 수행하면 H2에 데이터가 그대로 남아있어 테스트가 실패할 가능성이 있음
    public void cleanup(){
        memberRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given

        memberRepository.save(Member.builder() // posts에 insert/update 쿼리를 실행. id값이 있다면 update 없다면 insert
                .email("ekehd258@gmail.com")
                .password("1234")
                .name("KamDaGong")
                .nickName("prim")
                .build()
        );

        //when
        List<Member> memberList = memberRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Member member = memberList.get(0);
        assertThat(member.getEmail()).isEqualTo("ekehd258@gmail.com");
        assertThat(member.getPassword()).isEqualTo("1234");
    }

}
