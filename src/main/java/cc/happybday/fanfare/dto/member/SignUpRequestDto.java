package cc.happybday.fanfare.dto.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequestDto {
    private String username;
    private String nickname;
    private String password;
    private LocalDate birthDay;
}
