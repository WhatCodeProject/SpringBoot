package whatcode.study.whatcode.domain.room.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.room.RoomType;

@Getter
@NoArgsConstructor
public class RoomSaveRequestDto {

    private String teamName;
    private String roomName;
    private RoomType roomType;


    @Builder
    public RoomSaveRequestDto(String roomName, RoomType roomType, String teamName) {

        this.roomName = roomName;
        this.roomType = roomType;
        this.teamName = teamName;
    }


}
