package whatcode.study.whatcode.domain.team.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {

    @Autowired
    private MemberRepository memberRepository;

    private String memberEmail;
    private String teamName;
    private TeamType teamType;


    @Builder
    public TeamSaveRequestDto(String memberEmail, String teamName, TeamType teamType){
        this.memberEmail = memberEmail;
        this.teamName = teamName;
        this.teamType = teamType;
    }

    public Team toEntity(){
        System.out.println("여기 : " + this.memberEmail);
        Member master = memberRepository.findByEmail(this.memberEmail);
        TeamType tType;
        if(this.teamType.equals("STUDY")){
            tType = TeamType.STUDY;
        }else if(this.teamType.equals("WORK")){
            tType = TeamType.WORK;
        }else {
            tType = TeamType.STUDY;
        }
        return Team.createTeam(this.teamName, tType, master);
    }

    @Override
    public String toString() {
        return "TeamSaveRequestDto{" +
                "memberRepository=" + memberRepository +
                ", memberEmail='" + memberEmail + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamType='" + teamType + '\'' +
                '}';
    }
}
