package whatcode.study.whatcode.domain.code;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.room.Room;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public static Code createCode(String titie, CodeType codeType, String content, Room room, Member member) {
        Code code = new Code();
        code.title = titie;
        code.codeType = codeType;
        code.content = content;
        code.room = room;
        code.setMember(member);
        return code;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getCodes().add(this);
    }
}
