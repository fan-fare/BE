package cc.happybday.fanfare.dto.message;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AllMessageResponseListDto {

    private List<MessageResponseDto> messages;
    private Long totalCount;

    public static AllMessageResponseListDto toList(List<MessageResponseDto> messages, Long totalCount){
        return AllMessageResponseListDto.builder()
                .messages(messages)
                .totalCount(totalCount)
                .build();
    }
}
