package whatcode.study.whatcode.domain.memberRoom.memberRoomController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import whatcode.study.whatcode.domain.memberRoom.dtos.RoomFindRequestDto;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoom;
import whatcode.study.whatcode.domain.memberRoom.memberRoomService.MemberRoomService;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.memberTeam.memberTeamService.MemberTeamService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberRoomController {

    private final MemberRoomService memberRoomService;

    @PostMapping("/api/memberRoom/findRoomsByMember")
    public List<MemberRoom> findRoomsByMember(@RequestBody RoomFindRequestDto requestDto){
        return memberRoomService.findTeamsByMember(requestDto);
    }

}
