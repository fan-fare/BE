package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.dto.SignUpRequestDto;
import cc.happybday.fanfare.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.DUPLICATE_USERID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 아이디 중복 확인
    public boolean checkUsernameAvailability(String userId) {
        return memberRepository.findByUserId(userId).isEmpty();
    }

    // 회원가입
    public Long signUp(SignUpRequestDto request){
        if (!checkUsernameAvailability(request.getUserId())) {
            log.info("회원가입 실패 (중복된 아이디) : {}", request.getUserId());
            throw new BusinessException(DUPLICATE_USERID);
        }

        Member member = new Member();
        member.setUserId(request.getUserId());
        member.setNickname(request.getNickname());
        member.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        member.setBirthDay(request.getBirthDay());

        Member savedMember = memberRepository.save(member);

        log.info("회원가입 완료: {}", savedMember.getUserId());

        return savedMember.getId();
    }

}
