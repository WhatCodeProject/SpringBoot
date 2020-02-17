package whatcode.study.whatcode.domain.room.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoom;
import whatcode.study.whatcode.domain.team.team.Team;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    public List<Room> findByTeam(Team team);
}
