package whatcode.study.whatcode.domain.common;

import lombok.Data;

@Data
public class ReturnId {
    Long id;
    public ReturnId(Long id) {this.id = id;}
}
