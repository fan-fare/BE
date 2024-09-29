package cc.happybday.fanfare.dto.message;

import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MessageResponseDto {
    private Long messageId;
    private String content;
    private String senderNickname;
    private CandleColor candleColor;
    private LocalDate createdAt;


    public static MessageResponseDto toDto(Message message){
        return MessageResponseDto.builder()
                .messageId(message.getId())
                .content(message.getContent())
                .senderNickname(message.getNickname())
                .candleColor(message.getCandleColor())
                .createdAt(message.getCreatedAt())
                .build();
    }

}
