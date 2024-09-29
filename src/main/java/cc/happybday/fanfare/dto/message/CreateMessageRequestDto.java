package cc.happybday.fanfare.dto.message;

import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Message;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class CreateMessageRequestDto {
    private UUID memberUuid;
    private CandleColor color;
    private String content;
    private String nickname;
    private LocalDate createdAt;

    public Message toMessage(Member member) {
        return Message.builder()
                .member(member)
                .candleColor(this.color)
                .content(this.content)
                .nickname(this.nickname)
                .createdAt(LocalDate.now())
                .build();
    }
}
