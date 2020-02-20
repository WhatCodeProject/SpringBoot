package whatcode.study.whatcode.domain.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.room.dtos.RoomSaveRequestDto;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final TeamRepository teamRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Long save(RoomSaveRequestDto requestDto) {
        String roomName = requestDto.getRoomName();
        RoomType roomType = requestDto.getRoomType();
        Team team = teamRepository.findByTeamName(requestDto.getTeamName());

        return roomRepository.save(Room.createRoom(roomName, roomType, team)).getId();
    }

}
