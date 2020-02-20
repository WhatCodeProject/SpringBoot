package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamFindRequestDto {
    private Long member_id;

    @Builder
    public TeamFindRequestDto(Long member_id){
        this.member_id = member_id;
    }

}
