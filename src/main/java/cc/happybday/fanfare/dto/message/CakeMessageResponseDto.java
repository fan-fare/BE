package cc.happybday.fanfare.dto.message;

import cc.happybday.fanfare.domain.CandleColor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CakeMessageResponseDto {
    private Long messageId;
    private String senderNickname;
    private CandleColor candleColor;
}
