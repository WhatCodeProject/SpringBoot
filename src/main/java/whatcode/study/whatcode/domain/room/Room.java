package whatcode.study.whatcode.domain.room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.chat.Chat;
import whatcode.study.whatcode.domain.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.team.Team;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "room")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // ==== 생성 메서드 ===
    public static Room createRoom(String roomName, RoomType roomType, Team team){
        Room room = new Room();
        room.roomName = roomName;
        room.roomType = roomType;
        room.team = team;

        return room;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

}
