package whatcode.study.whatcode.domain.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginRequestDto;
import whatcode.study.whatcode.domain.member.dtos.MemberLoginResponseDto;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void memberSave() throws Exception {
        //given
        String email = "test0101@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(email);

        //when && then
        this.mockMvc.perform(post("/api/member/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists());

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

        this.mockMvc.perform(post("/api/member/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberLoginDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("nickName").value("Stranger"));

    }

    @AfterEach
    public void tearDown() throws Exception{
        memberRepository.deleteAll();
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
