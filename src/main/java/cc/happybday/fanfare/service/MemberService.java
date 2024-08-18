package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.dto.SignUpRequestDto;
import cc.happybday.fanfare.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.DUPLICATE_NICKNAME;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    // 닉네임 중복 확인
    public boolean isNicknameTaken(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }

    // 회원가입
    public Long signUp(SignUpRequestDto request){
        if (isNicknameTaken(request.getNickname())) {
            throw new BusinessException(DUPLICATE_NICKNAME);
        }

        Member member = new Member();
        member.setUserId(request.getUserId());
        member.setNickname(request.getNickname());
        member.setPassword(request.getPassword());
        member.setBirthDay(request.getBirthDay());

        Member savedMember = memberRepository.save(member);
        log.info("회원가입 완료: {}", savedMember);

        return savedMember.getId();
    }

}
