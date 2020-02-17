package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamFindRequestDto {
    private String email;

    @Builder
    public TeamFindRequestDto(String email){
        this.email = email;
    }

}
