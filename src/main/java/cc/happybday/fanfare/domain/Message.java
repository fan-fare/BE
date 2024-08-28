package cc.happybday.fanfare.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Column(length = 300)
    private String content;

    @Column(length = 20)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private CandleColor candleColor;

}
