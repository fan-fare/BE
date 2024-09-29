package cc.happybday.fanfare.dto.member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class MemberInfoDto {
    UUID memberUuid;
    String username;
    LocalDate birthDay;
}
