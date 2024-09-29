package cc.happybday.fanfare.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)", updatable = true, nullable = true, unique = true)
    private UUID uuid;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private Role role;


}
