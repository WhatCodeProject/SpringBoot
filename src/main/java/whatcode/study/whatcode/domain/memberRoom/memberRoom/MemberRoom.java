package whatcode.study.whatcode.domain.memberRoom.memberRoom;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.common.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.room.room.Room;
import whatcode.study.whatcode.domain.team.team.Team;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_room_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    // ==== 생성 메서드 ==== //
    public static MemberRoom createMemberRoom(Member member, Room room){
        MemberRoom memberRoom = new MemberRoom();
        memberRoom.setMember(member);
        memberRoom.setRoom(room);
        return memberRoom;
    }


    /*
        연관 관계편의 메서드로 MemberRoom이 생성될 때 각 Member, Room의 MemberRooms 값에도 추가해준다.
     */
    private void setMember(Member member){
        this.member = member;
        member.getMemberRooms().add(this);
    }

    private void setRoom(Room room){
        this.room = room;
        room.getMemberRooms().add(this);
    }

}
