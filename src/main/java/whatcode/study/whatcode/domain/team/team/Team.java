package whatcode.study.whatcode.domain.team.team;

import lombok.AccessLevel;

import lombok.*;
import whatcode.study.whatcode.domain.chat.chat.Chat;
import whatcode.study.whatcode.domain.common.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<MemberTeam> memberTeams = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member master;


    // ==== 생성 메서드 ===
    public static Team createTeam(String teamName, TeamType teamType, Member master){
        Team team = new Team();
        team.teamName = teamName;
        team.teamType = teamType;
        team.master = master;

        return team;
    }
}
