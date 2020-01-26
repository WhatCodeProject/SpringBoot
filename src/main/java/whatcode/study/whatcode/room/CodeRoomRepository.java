package whatcode.study.whatcode.room;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CodeRoomRepository {

    private final Map<String, CodeRoom> codeRoomMap;

    private final List<CodeRoom> codeRooms;

    public CodeRoomRepository() {
        codeRoomMap = Collections
                .unmodifiableMap(getCodeRoomStream()
                        .collect(Collectors.toMap(CodeRoom::getRoomId, Function.identity())));

        codeRooms = new ArrayList<>(codeRoomMap.values());
    }

    public CodeRoom findById(String id){
        return codeRoomMap.get(id);
    }

    public List<CodeRoom> findAll(){
        return codeRooms;
    }

    private Stream<CodeRoom> getCodeRoomStream() {
        return Stream.of(
                CodeRoom.createCodeRoom("A Proejct Code Room"),
                CodeRoom.createCodeRoom("B Proejct Code Room"),
                CodeRoom.createCodeRoom("C Proejct Code Room"),
                CodeRoom.createCodeRoom("D Proejct Code Room"),
                CodeRoom.createCodeRoom("E Proejct Code Room")
        );
    }
}
