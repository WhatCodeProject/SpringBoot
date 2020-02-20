package whatcode.study.whatcode.domain.memberTeam.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberTeamSaveRequestDto {
    private String email;
    private String teamName;

    @Builder
    public MemberTeamSaveRequestDto(String email, String teamName){
        this.email = email;
        this.teamName = teamName;
    }
}
