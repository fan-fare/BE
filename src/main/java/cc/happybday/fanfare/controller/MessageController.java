package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.domain.Message;
import cc.happybday.fanfare.dto.message.AllMessageResponseListDto;
import cc.happybday.fanfare.dto.message.CreateMessageRequestDto;
import cc.happybday.fanfare.dto.message.GetMessageResponseDto;
import cc.happybday.fanfare.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public BaseResponse<Long> createMessage(@RequestBody CreateMessageRequestDto request) {
        Long messageId = messageService.saveMessage(request);
        return new BaseResponse<>(messageId, BaseResponseCode.SUCCESS);
    }

    // 특정 메세지 1개 조회
    @GetMapping("/message/{message_id}")
    public BaseResponse<GetMessageResponseDto> readMessage(@PathVariable Long message_id) {
        GetMessageResponseDto message = messageService.readMessage(message_id);
        return new BaseResponse<>(message, BaseResponseCode.SUCCESS);
    }

    // 범위에 따른 메세지 조회
    @GetMapping("/messages")
    public BaseResponse<AllMessageResponseListDto> readMessage(
            @RequestParam UUID member_uuid,
            @RequestParam(defaultValue = "-1") Long start,
            @RequestParam(defaultValue = "-1") Long end
    ) {
        AllMessageResponseListDto messages = messageService.readAllMessages(member_uuid, start, end);
        return new BaseResponse<>(messages, BaseResponseCode.SUCCESS);
    }

    @DeleteMapping("/message/{message_id}")
    public BaseResponse<String> deleteMessage(@PathVariable Long message_id) {
        String response = messageService.deleteMessage(message_id);
        return new BaseResponse<>(response, BaseResponseCode.SUCCESS);
    }
}
