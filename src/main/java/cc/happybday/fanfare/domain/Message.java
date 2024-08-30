package cc.happybday.fanfare.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID", nullable = false)
    private Member member;

    @Column(length = 300, nullable = false)
    private String content;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CandleColor candleColor;

    @Column(nullable = false)
    private LocalDate createdAt;

}
