package cc.happybday.fanfare.dto.message;

import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Message;
import lombok.Data;

@Data
public class CreateMessageRequestDto {
    private Long memberId;
    private CandleColor color;
    private String content;
    private String nickname;

    public Message toMessage(Member member) {
        return Message.builder()
                .member(member)
                .candleColor(this.color)
                .content(this.content)
                .nickname(this.nickname)
                .build();
    }
}
