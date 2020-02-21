package whatcode.study.whatcode.domain.memberTeam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.member.dtos.MemberSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.MemberTeamSaveRequestDto;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.team.TeamRepository;
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
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    MemberTeamRepository memberTeamRepository;

    @Autowired
    MemberTeamService memberTeamService;


    @Test
    void joinTeam()throws Exception{
        //init master member
        String memberEmail = "test01@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(memberEmail);
        Long member_id1 = memberService.save(memberDto);

        //init 3 teams
        String teamName1 = "WhatCodeTeam_01";
        String teamName2 = "WhatCodeTeam_02";
        String teamName3 = "WhatCodeTeam_03";
        TeamSaveRequestDto teamDto1 = getTeamSaveRequestDto(member_id1, teamName1);
        TeamSaveRequestDto teamDto2 = getTeamSaveRequestDto(member_id1, teamName2);
        TeamSaveRequestDto teamDto3 = getTeamSaveRequestDto(member_id1, teamName3);
        Long team_id1 = teamService.save(teamDto1);
        Long team_id2 = teamService.save(teamDto2);
        Long team_id3 = teamService.save(teamDto3);

        //init test member
        String otherEmail = "other01@gmail.com";
        MemberSaveRequestDto otherDto = getMemberSaveRequestDto(otherEmail);
        Long testMember_id1 = memberService.save(otherDto);

        //given
        MemberTeamSaveRequestDto memberTeamDto = getMemberTeamSaveRequestDto(testMember_id1, team_id1);

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
        //init master member
        String memberEmail = "test01@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(memberEmail);
        Long member_id1 = memberService.save(memberDto);

        //init 3 teams
        String teamName1 = "WhatCodeTeam_01";
        String teamName2 = "WhatCodeTeam_02";
        String teamName3 = "WhatCodeTeam_03";
        TeamSaveRequestDto teamDto1 = getTeamSaveRequestDto(member_id1, teamName1);
        TeamSaveRequestDto teamDto2 = getTeamSaveRequestDto(member_id1, teamName2);
        TeamSaveRequestDto teamDto3 = getTeamSaveRequestDto(member_id1, teamName3);
        Long team_id1 = teamService.save(teamDto1);
        Long team_id2 = teamService.save(teamDto2);
        Long team_id3 = teamService.save(teamDto3);

        //init test member
        String otherEmail = "other01@gmail.com";
        MemberSaveRequestDto otherDto = getMemberSaveRequestDto(otherEmail);
        Long testMember_id1 = memberService.save(otherDto);


        //given
        MemberTeamSaveRequestDto memberTeamDto1 = getMemberTeamSaveRequestDto(testMember_id1,team_id1);
        MemberTeamSaveRequestDto memberTeamDto2 = getMemberTeamSaveRequestDto(testMember_id1,team_id2);
        MemberTeamSaveRequestDto memberTeamDto3 = getMemberTeamSaveRequestDto(testMember_id1,team_id3);

        memberTeamService.save(memberTeamDto1);
        memberTeamService.save(memberTeamDto2);
        memberTeamService.save(memberTeamDto3);

        TeamFindRequestDto requestDto = getTeamFindRequestDto(testMember_id1);

        //when && then
        this.mockMvc.perform(post("/api/memberTeam/findTeamsByMember")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @AfterEach
    void tearDown(){
        memberTeamRepository.deleteAll();
        memberRepository.deleteAll();
        teamRepository.deleteAll();
    }

    private TeamFindRequestDto getTeamFindRequestDto(Long member_id) {
        return TeamFindRequestDto.builder()
                .member_id(member_id)
                .build();
    }

    private MemberTeamSaveRequestDto getMemberTeamSaveRequestDto(Long member_id, Long team_id) {
        return MemberTeamSaveRequestDto.builder()
                .member_id(member_id)
                .team_id(team_id)
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

    private TeamSaveRequestDto getTeamSaveRequestDto(Long member_id, String teamName) {
        return TeamSaveRequestDto.builder()
                .member_id(member_id)
                .teamName(teamName)
                .teamType(TeamType.WORK)
                .build();
    }
}
