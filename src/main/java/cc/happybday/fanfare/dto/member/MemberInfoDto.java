package cc.happybday.fanfare.dto.member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MemberInfoDto {
    Long memberId;
    String username;
    LocalDate birthDay;
}
