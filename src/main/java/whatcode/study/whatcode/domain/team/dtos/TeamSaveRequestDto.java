package whatcode.study.whatcode.domain.team.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {

    private Long member_id;
    private String teamName;
    private TeamType teamType;


    @Builder
    public TeamSaveRequestDto(Long member_id, String teamName, TeamType teamType){
        this.member_id = member_id;
        this.teamName = teamName;
        this.teamType = teamType;
    }

}
