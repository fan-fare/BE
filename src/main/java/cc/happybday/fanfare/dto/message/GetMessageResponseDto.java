package cc.happybday.fanfare.dto.message;

import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Message;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMessageResponseDto {
    private String content;
    private String nickname;
    private CandleColor candleColor;
    private Long beforeMessageId;
    private Long nextMessageId;


    public static GetMessageResponseDto toDto(Message message, Long beforeMessageId, Long nextMessageId){
        return GetMessageResponseDto.builder()
                .content(message.getContent())
                .nickname(message.getNickname())
                .candleColor(message.getCandleColor())
                .beforeMessageId(beforeMessageId)
                .nextMessageId(nextMessageId)
                .build();
    }
}
