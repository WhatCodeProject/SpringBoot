package whatcode.study.whatcode.domain.member.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.chat.chat.Chat;
import whatcode.study.whatcode.domain.common.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoom;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String nickName;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTeam> memberTeams = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberRoom> memberRooms = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name, String nickName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
