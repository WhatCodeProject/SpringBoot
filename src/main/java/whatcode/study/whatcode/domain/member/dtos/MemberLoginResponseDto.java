package whatcode.study.whatcode.domain.member.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.member.Member;

@Getter
@NoArgsConstructor
public class MemberLoginResponseDto {
    private Long id;
    private String email;
    private String name;
    private String nickName;

    @Builder
    public MemberLoginResponseDto(Long id, String email, String name, String nickName){
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }

}
