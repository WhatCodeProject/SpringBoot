package whatcode.study.whatcode.domain.room;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.team.TeamRepository;
import whatcode.study.whatcode.domain.team.TeamService;
import whatcode.study.whatcode.domain.team.TeamType;
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
class RoomApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void initTeam(){
        String memberEmail = "test01@gmail.com";
        MemberSaveRequestDto memberDto = getMemberSaveRequestDto(memberEmail);
        memberService.save(memberDto);

        String teamName ="WhatCodeTeam_01";
        TeamSaveRequestDto teamDto = getTeamSaveRequestDto(memberEmail, teamName);
        teamService.save(teamDto);
    }


    @Test
    void roomSave() throws Exception{

        //given
        String inRoomName = "코드방1234";
        String inTeamName = "WhatCodeTeam_01";

        RoomSaveRequestDto roomDto = getRoomSaveRequestDto(inRoomName,inTeamName);

        //when && then
        this.mockMvc.perform(post("/api/room/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDto)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("id").value("1"));

    }

    private RoomSaveRequestDto getRoomSaveRequestDto(String inRoomName, String inTeamName) {
        return RoomSaveRequestDto.builder()
                .roomName(inRoomName)
                .roomType(RoomType.JAVA)
                .teamName(inTeamName)
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
