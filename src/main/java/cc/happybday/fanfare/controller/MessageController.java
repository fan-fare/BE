package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.domain.Message;
import cc.happybday.fanfare.dto.message.CreateMessageRequestDto;
import cc.happybday.fanfare.dto.message.GetMessageResponseDto;
import cc.happybday.fanfare.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public BaseResponse<Long> createMessage(@RequestBody CreateMessageRequestDto request) {
        Long messageId = messageService.saveMessage(request);
        return new BaseResponse<>(messageId, BaseResponseCode.SUCCESS);
    }

    @GetMapping("/message/{id}")
    public BaseResponse<GetMessageResponseDto> readMessage(@PathVariable Long id) {
        GetMessageResponseDto message = messageService.readMessage(id);
        return new BaseResponse<>(message, BaseResponseCode.SUCCESS);
    }

    @DeleteMapping("/message/{id}")
    public BaseResponse<String> deleteMessage(@PathVariable Long id) {
        String response = messageService.deleteMessage(id);
        return new BaseResponse<>(response, BaseResponseCode.SUCCESS);
    }
}
