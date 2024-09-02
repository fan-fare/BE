package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.dto.cake.CakeResponseDto;
import cc.happybday.fanfare.service.MemberService;
import cc.happybday.fanfare.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CakeController {

    private final MemberService memberService;
    private final MessageService messageService;

    @GetMapping("/cake/{memberId}")
    public CakeResponseDto mainCake(@PathVariable Long memberId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {

        Long totalMessageCount = messageService.getMessageTotalCount(memberId);
        Long totalCakeCount = (long) Math.ceil((double) totalMessageCount / size);
        Member member = memberService.getMember(memberId);
        return CakeResponseDto.builder()
                .totalCakeCount(totalCakeCount)
                .totalMessageCount(totalMessageCount)
                .messageIdList(messageService.getMessageIdList(memberId, page, size))
                .nickname(member.getNickname())
                .birthDay(member.getBirthDay())
                .build();
    }
}
