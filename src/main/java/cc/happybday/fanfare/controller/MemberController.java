package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.common.response.BaseResponse;
import cc.happybday.fanfare.common.response.BaseResponseCode;
import cc.happybday.fanfare.dto.member.MemberInfoDto;
import cc.happybday.fanfare.dto.member.SignUpRequestDto;
import cc.happybday.fanfare.dto.security.CustomUserDetails;
import cc.happybday.fanfare.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/exists")
    public BaseResponse<Boolean> checkUsernameExists(@RequestParam String id) {
        boolean isExists = memberService.isUsernameExists(id); // 존재하면 true
        return new BaseResponse<>(isExists, BaseResponseCode.SUCCESS);
    }

    @PostMapping("/signup")
    public BaseResponse<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        String memberUuid = memberService.signUp(signUpRequestDto).toString();
        return new BaseResponse<>(memberUuid, BaseResponseCode.SUCCESS);
    }

    @GetMapping("/me")
    public BaseResponse<MemberInfoDto> getCurrentMemberInfo() {
        MemberInfoDto memberInfoDto = memberService.getCurrentMemberInfo();
        return new BaseResponse<>(memberInfoDto, BaseResponseCode.SUCCESS);
    }

}
