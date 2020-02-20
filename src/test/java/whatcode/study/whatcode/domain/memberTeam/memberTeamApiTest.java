package whatcode.study.whatcode.domain.memberTeam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.MemberTeamSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.team.TeamService;
import whatcode.study.whatcode.domain.team.TeamType;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class memberTeamApiTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;

    @Autowired
    TeamService teamService;

    @Autowired
    MemberTeamService memberTeamService;

    @BeforeEach
    void initMemberTeam() {
        //init master member
        String memberEmail = "test01@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(memberEmail);
        memberService.save(memberDto);

        //init 3 teams
        String teamName1 = "WhatCodeTeam_01";
        String teamName2 = "WhatCodeTeam_02";
        String teamName3 = "WhatCodeTeam_03";
        TeamSaveRequestDto teamDto1 = getTeamSaveRequestDto(memberEmail, teamName1);
        TeamSaveRequestDto teamDto2 = getTeamSaveRequestDto(memberEmail, teamName2);
        TeamSaveRequestDto teamDto3 = getTeamSaveRequestDto(memberEmail, teamName3);
        teamService.save(teamDto1);
        teamService.save(teamDto2);
        teamService.save(teamDto3);

        //init test member
        String otherEmail = "other01@gmail.com";
        MemberSaveRequestDto otherDto = getMemberSaveRequestDto(otherEmail);
        memberService.save(otherDto);
    }

    @Test
    void joinTeam()throws Exception{
        //given
        String otherEmail = "other01@gmail.com";
        String teamName1 = "WhatCodeTeam_01";

        MemberTeamSaveRequestDto memberTeamDto = getMemberTeamSaveRequestDto(otherEmail,teamName1);

        //when && then
        this.mockMvc.perform(post("/api/memberTeam/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberTeamDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists());
    }

    @Test
    void findTeamsByMember()throws Exception{
        //given
        String otherEmail = "other01@gmail.com";
        String teamName1 = "WhatCodeTeam_01";
        String teamName2 = "WhatCodeTeam_02";
        String teamName3 = "WhatCodeTeam_03";

        MemberTeamSaveRequestDto memberTeamDto1 = getMemberTeamSaveRequestDto(otherEmail,teamName1);
        MemberTeamSaveRequestDto memberTeamDto2 = getMemberTeamSaveRequestDto(otherEmail,teamName2);
        MemberTeamSaveRequestDto memberTeamDto3 = getMemberTeamSaveRequestDto(otherEmail,teamName3);

        memberTeamService.save(memberTeamDto1);
        memberTeamService.save(memberTeamDto2);
        memberTeamService.save(memberTeamDto3);

        TeamFindRequestDto requestDto = getTeamFindRequestDto(otherEmail);

        //when && then
        this.mockMvc.perform(post("/api/memberTeam/findTeamsByMember")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private TeamFindRequestDto getTeamFindRequestDto(String otherEmail) {
        return TeamFindRequestDto.builder()
                .email(otherEmail)
                .build();
    }

    private MemberTeamSaveRequestDto getMemberTeamSaveRequestDto(String otherEmail, String teamName) {
        return MemberTeamSaveRequestDto.builder()
                .email(otherEmail)
                .teamName(teamName)
                .build();
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
