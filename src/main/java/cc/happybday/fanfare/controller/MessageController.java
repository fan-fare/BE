package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.dto.message.CreateMessageRequestDto;
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
}
