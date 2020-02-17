package whatcode.study.whatcode.socket.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RoomRepository {

    private final Map<String, Room> roomMap;

    private final List<Room> roomList;

    public RoomRepository(){
        roomMap = Collections
                .unmodifiableMap(getRoomAsStream()
                .collect(Collectors.toMap(Room::getRoomId, Function.identity())));

        roomList = new ArrayList<>(roomMap.values());
    }

    public List<Room> findAll(){
        return roomList;
    }

    public Room findById(String id){
        return roomMap.get(id);
    }

    private Stream<Room> getRoomAsStream(){
        return Stream.of(
                new Room("FrontEnd Room"),
                new Room("BackEnd Room"),
                new Room("Sample Room"),
                new Room("Algorithm Room")
        );
    }
}
