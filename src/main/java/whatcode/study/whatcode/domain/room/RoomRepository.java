package whatcode.study.whatcode.domain.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.team.Team;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    public List<Room> findByTeam(Team team);
    public Room findByTeam(Team team, String roomName);
}
