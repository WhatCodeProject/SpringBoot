package whatcode.study.whatcode.domain.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.MemberTeamRepository;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TeamApiTest {
    @Autowired
    EntityManager em;

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

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
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

        // when && than
        this.mockMvc.perform(post("/api/team/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDto)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("id").exists());
    }

    @AfterEach
    void tearDown() throws Exception{
        memberTeamRepository.deleteAll();
        teamRepository.deleteAll();
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

    private TeamSaveRequestDto getTeamSaveRequestDto(String email, String teamName) {
        return TeamSaveRequestDto.builder()
                .memberEmail(email)
                .teamName(teamName)
                .teamType(TeamType.WORK)
                .build();
    }

}