package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberTeamSaveRequestDto {
    private Long member_id;
    private Long team_id;

    @Builder
    public MemberTeamSaveRequestDto(Long member_id, Long team_id){
        this.member_id = member_id;
        this.team_id = team_id;
    }
}
