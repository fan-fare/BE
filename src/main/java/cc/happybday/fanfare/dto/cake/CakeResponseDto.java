package cc.happybday.fanfare.dto.cake;

import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.dto.message.CakeMessageResponseDto;
import cc.happybday.fanfare.dto.message.MessageResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CakeResponseDto {
    String nickname;
    LocalDate birthDay;
    List<CakeMessageResponseDto> messages;
}
