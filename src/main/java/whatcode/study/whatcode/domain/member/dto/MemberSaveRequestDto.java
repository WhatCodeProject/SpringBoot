package whatcode.study.whatcode.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.member.member.Member;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickName;

    @Builder
    public MemberSaveRequestDto(String email,String password,String name,String nickName){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .build();
    }
}
