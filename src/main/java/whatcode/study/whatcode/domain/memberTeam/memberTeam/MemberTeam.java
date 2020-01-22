package whatcode.study.whatcode.domain.memberTeam.memberTeam;

import lombok.*;
import whatcode.study.whatcode.domain.common.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.team.team.Team;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberTeam extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_team_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // ==== 생성 메서드 ==== //
    public static MemberTeam createMemberTeam(Member member, Team team){
        MemberTeam memberTeam = new MemberTeam();
        memberTeam.setMember(member);
        memberTeam.setTeam(team);
        return memberTeam;
    }


    /*
        연관 관계편의 메서드로 MemberTeam이 생성될 때 각 Member, Team의 MemberTeams 값에도 추가해준다.
     */
    private void setMember(Member member){
        this.member = member;
        member.getMemberTeams().add(this);
    }

    private void setTeam(Team team){
        this.team = team;
        team.getMemberTeams().add(this);
    }

}
