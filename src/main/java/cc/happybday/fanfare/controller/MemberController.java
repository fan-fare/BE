package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.dto.SignUpRequestDto;
import cc.happybday.fanfare.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/check-nickname")
    public BaseResponse<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isTaken = memberService.isNicknameTaken(nickname);
        return new BaseResponse<>(isTaken, BaseResponseCode.SUCCESS);
    }

    @PostMapping("/signup")
    public BaseResponse<Long> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        Long memberId = memberService.signUp(signUpRequestDto);
        return new BaseResponse<>(memberId, BaseResponseCode.SUCCESS);
    }

}
