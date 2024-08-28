package cc.happybday.fanfare.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(length = 20)
    private String memberId;

    @Column(length = 20)
    private String nickname;

    @Column(length = 255)
    private String password;

    private LocalDate birthDay;

}
