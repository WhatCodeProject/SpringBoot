package whatcode.study.whatcode.domain.room.roomService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.room.room.Room;
import whatcode.study.whatcode.domain.room.room.RoomRepository;
import whatcode.study.whatcode.domain.room.room.RoomType;
import whatcode.study.whatcode.domain.team.dtos.TeamSaveRequestDto;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamRepository;
import whatcode.study.whatcode.domain.team.team.TeamType;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Long save(RoomSaveRequestDto requestDto){
        String roomName = requestDto.getRoomName();
        Team team = teamRepository.findByTeamName(requestDto.getTeamName());
        Member master = memberRepository.findByEmail(requestDto.getMemberEmail());

        RoomType roomType;
        if(requestDto.getRoomType().equals("JAVA")){
            roomType = RoomType.JAVA;
        }else if(requestDto.getRoomType().equals("C")){
            roomType = RoomType.C;
        }else if(requestDto.getRoomType().equals("JS")){
            roomType = RoomType.JS;
        } else {
            roomType = RoomType.COMMON;
        }

        return roomRepository.save(Room.createRoom(roomName,roomType,master,team)).getId();
    }

}
