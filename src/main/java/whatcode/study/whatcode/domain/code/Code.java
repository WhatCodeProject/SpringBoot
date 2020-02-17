package whatcode.study.whatcode.domain.code;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatcode.study.whatcode.domain.common.BaseTimeEntity;
import whatcode.study.whatcode.domain.team.Team;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    // TODO 코드는 룸, 멤버와 연관관계

    @Builder
    public Code(String title, CodeType codeType, String content, Team team) {
        this.title = title;
        this.codeType = codeType;
        this.content = content;
        this.team = team;
    }
}
