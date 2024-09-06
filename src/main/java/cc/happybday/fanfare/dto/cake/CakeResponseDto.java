package cc.happybday.fanfare.dto.cake;

import cc.happybday.fanfare.domain.CandleColor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CakeResponseDto {
    Long totalCakeCount;
    String nickname;
    LocalDate birthDay;
    Long totalMessageCount;
    List<Long> messageIdList;
    List<String> messageSenderNicknameList;
    List<CandleColor> candleColorsList;
}
