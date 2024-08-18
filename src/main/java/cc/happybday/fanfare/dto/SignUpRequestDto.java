package cc.happybday.fanfare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequestDto {
    private String userId;
    private String nickname;
    private String password;
    private LocalDate birthDay;
}
