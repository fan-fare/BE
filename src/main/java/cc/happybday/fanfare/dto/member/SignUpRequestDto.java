package cc.happybday.fanfare.dto.member;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestDto {
    private String username;
    private String nickname;
    private String password;
    private LocalDate birthDay;
}
