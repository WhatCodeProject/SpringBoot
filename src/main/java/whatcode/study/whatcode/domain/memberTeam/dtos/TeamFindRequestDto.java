package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamFindRequestDto {
    private String email;

    @Builder
    public TeamFindRequestDto(String email){
        this.email = email;
    }

}
