package whatcode.study.whatcode.domain.room.room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.chat.chat.Chat;
import whatcode.study.whatcode.domain.common.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoom;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.team.team.Team;
import whatcode.study.whatcode.domain.team.team.TeamType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<MemberRoom> memberRooms = new ArrayList<>();

    @OneToOne(mappedBy = "room")
    private Chat chat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member master;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    // ==== 생성 메서드 ===
    public static Room createRoom(String roomName, RoomType roomType, Member master, Team team){
        Room room = new Room();
        room.roomName = roomName;
        room.roomType = roomType;
        room.master = master;
        room.team = team;

        return room;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
