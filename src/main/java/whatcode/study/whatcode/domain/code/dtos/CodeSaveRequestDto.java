package whatcode.study.whatcode.domain.code.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import whatcode.study.whatcode.domain.code.Code;
import whatcode.study.whatcode.domain.code.CodeType;
import whatcode.study.whatcode.domain.member.Member;
import whatcode.study.whatcode.domain.member.MemberRepository;
import whatcode.study.whatcode.domain.member.MemberService;
import whatcode.study.whatcode.domain.room.Room;
import whatcode.study.whatcode.domain.room.RoomRepository;
import whatcode.study.whatcode.domain.team.Team;
import whatcode.study.whatcode.domain.team.TeamRepository;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class CodeSaveRequestDto {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoomRepository roomRepository;

    private Long room_id;
    private String title;
    private CodeType codeType;
    private String content;
    private String roomName;
    private String email;

    @Builder
    public CodeSaveRequestDto(String title, CodeType codeType, String content, String roomName, String teamName, String email){
        this.title = title;
        this.codeType = codeType;
        this.content = content;
        this.roomName = roomName;
        this.email = email;
    }

    public Code toEntity(){
        Optional<Room> roomOpt = roomRepository.findById(room_id);
        Member member = memberRepository.findByEmail(email);
        return Code.createCode(title,codeType,content,roomOpt.get(),member);
    }
}
