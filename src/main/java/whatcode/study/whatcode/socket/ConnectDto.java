package whatcode.study.whatcode.socket;

import lombok.Data;

import java.util.Set;

@Data
public class ConnectDto {
    private Set<String> member;
    private String code;

    public ConnectDto(Set<String> member, String code) {
        if (code == null) {
            code = "";
        }
        this.member = member;
        this.code = code;
    }
}
