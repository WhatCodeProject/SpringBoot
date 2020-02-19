package whatcode.study.whatcode.domain.member;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginRequestDto;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginResponseDto;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
class MemberApiTest {
    private final String port = "8081";

    @Autowired
    EntityManager em;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @After
    public void tearDown() throws Exception{
        memberRepository.deleteAll();
    }

    @Test
    void memberSave() throws Exception {
        //given
        String email = "test0101@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(email);

        //when
        String url = "http://localhost:" + port + "/api/member/save";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, memberDto, Long.class);

        //than
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Member resMember = memberRepository.getOne(responseEntity.getBody());
        assertThat(resMember.getEmail())
                .isEqualTo(email);

    }

    @Test
    void memberLogin() throws Exception {
        //given
        //예정된 패스워드 1234(getMemberSaveRequestDto)
        String email = "test0101@gmail.com";
        String testPassword = "1234";

        MemberSaveRequestDto memberSaveDto = getMemberSaveRequestDto(email);
        Long savedMemberId = memberService.save(memberSaveDto);

        MemberLoginRequestDto memberLoginDto = getMemberLoginRequestDto(email,testPassword);

        em.flush();
        em.clear();

        //when
        String url = "http://localhost:" + port + "/api/member/login";
        ResponseEntity<MemberLoginResponseDto> responseEntity = restTemplate.postForEntity(url, memberLoginDto, MemberLoginResponseDto.class);

        //than
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getNickName())
                .isEqualTo("Stranger");
    }

    private MemberSaveRequestDto getMemberSaveRequestDto(String email) {
        return MemberSaveRequestDto.builder()
                .email(email)
                .password("1234")
                .name("Dexter")
                .nickName("Stranger")
                .build();
    }

    private MemberLoginRequestDto getMemberLoginRequestDto(String email, String password) {
        return MemberLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
