package whatcode.study.whatcode.domain.chat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.team.Team;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime chatDate;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    // TODO Chat은 Room이랑 연관관계

    // ==== 생성 메서드 ==== //
    public static Chat createChat(String content, LocalDateTime chatDate, Member member, Team team){
        Chat chat = new Chat();
        chat.content = content;
        chat.chatDate = chatDate;
        chat.setMember(member);

        return chat;
    }

    // ==== 연관관계 편의 메서드 ==== //
    private void setMember(Member member) {
        this.member = member;
        member.getChats().add(this);
    }

//    private void setTeam(Team team) {
//        this.team = team;
//        team.setChat(this);
//    }
}
