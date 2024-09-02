package cc.happybday.fanfare.dto.cake;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CakeResponseDto {
    Long totalMessageCount;
    Long totalCakeCount;
    List<Long> messageIdList;
    String nickname;
    LocalDate birthDay;
}
