package whatcode.study.whatcode.domain.memberRoom.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomFindRequestDto {
    private String email;
    private String teamName;

    @Builder
    public RoomFindRequestDto(String email, String teamName){
        this.email = email;
        this.teamName = teamName;
    }

}
