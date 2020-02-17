package whatcode.study.whatcode.domain.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamRepository;

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
