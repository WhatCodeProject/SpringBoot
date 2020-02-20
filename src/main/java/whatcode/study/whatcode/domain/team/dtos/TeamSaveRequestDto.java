package whatcode.study.whatcode.domain.team.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {


    private String memberEmail;
    private String teamName;
    private TeamType teamType;


    @Builder
    public TeamSaveRequestDto(String memberEmail, String teamName, TeamType teamType){
        this.memberEmail = memberEmail;
        this.teamName = teamName;
        this.teamType = teamType;
    }

}
