package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.dto.cake.CakeResponseDto;
import cc.happybday.fanfare.service.MemberService;
import cc.happybday.fanfare.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CakeController {

    private final MemberService memberService;
    private final MessageService messageService;

    @GetMapping("/cake/{memberUuid}")
    public BaseResponse<CakeResponseDto> mainCake(@PathVariable UUID memberUuid,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        Member member = memberService.getMemberByUuid(memberUuid);
        CakeResponseDto response = messageService.getCake(member, page, size);
        return new BaseResponse<>(response, BaseResponseCode.SUCCESS);
    }
}
