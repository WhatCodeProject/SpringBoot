package whatcode.study.whatcode.domain.team.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamType;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {

    @Autowired
    private MemberRepository memberRepository;

    private String memberEmail;
    private String teamName;
    private String teamType;


    @Builder
    public TeamSaveRequestDto(String memberEmail, String teamName, String teamType){
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
