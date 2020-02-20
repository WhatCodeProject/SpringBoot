package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamFindResponseDto {
    private Long id;
    private String teamName;
    private TeamType teamType;

    @Builder
    public TeamFindResponseDto(Long id, String teamName, TeamType teamType){
        this.id = id;
        this.teamName = teamName;
        this.teamType = teamType;
    }
}
