package whatcode.study.whatcode.domain.member.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.member.member.Member;

@Getter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;

    @Builder
    public MemberLoginRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
