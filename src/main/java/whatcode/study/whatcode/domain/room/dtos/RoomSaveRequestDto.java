package whatcode.study.whatcode.domain.room.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import whatcode.study.whatcode.domain.member.MemberRepository;

@Getter
@NoArgsConstructor
public class RoomSaveRequestDto {

    @Autowired
    private MemberRepository memberRepository;

    private String memberEmail;
    private String teamName;
    private String roomName;
    private String roomType;


    @Builder
    public RoomSaveRequestDto(String memberEmail, String roomName, String roomType, String teamName){
        this.memberEmail = memberEmail;
        this.roomName = roomName;
        this.roomType = roomType;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "TeamSaveRequestDto{" +
                ", memberEmail='" + memberEmail + '\'' +
                ", teamName='" + teamName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }

}
