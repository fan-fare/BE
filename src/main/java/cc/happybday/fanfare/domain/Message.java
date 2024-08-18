package cc.happybday.fanfare.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter @Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Column(length = 300)
    private String content;

    @Column(length = 20)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Color color;

}
