package whatcode.study.whatcode.domain.member.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.member.Member;

@Getter
@NoArgsConstructor
public class MemberLoginResponseDto {
    private String email;
    private String name;
    private String nickName;

    @Builder
    public MemberLoginResponseDto(String email, String name, String nickName){
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .nickName(nickName)
                .build();
    }
}
