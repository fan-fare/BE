package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Role;
import cc.happybday.fanfare.dto.member.MemberInfoDto;
import cc.happybday.fanfare.dto.member.SignUpRequestDto;
import cc.happybday.fanfare.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.DUPLICATE_USERNAME;
import static cc.happybday.fanfare.common.response.ErrorResponseCode.MEMBER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 아이디 중복 확인
    public boolean isUsernameExists(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    // 회원가입
    public UUID signUp(SignUpRequestDto request){

        if (isUsernameExists(request.getUsername())) {
            log.info("회원가입 실패 (중복된 username) : {}", request.getUsername());
            throw new BusinessException(DUPLICATE_USERNAME);
        }

        Member member = Member.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .birthDay(request.getBirthDay())
                .role(Role.USER)
                .build();

        Member savedMember = memberRepository.save(member);

        log.info("회원가입 완료: {}", savedMember.getUsername());

        return savedMember.getUuid();
    }


    public Member getCurrentMember() throws BusinessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }
    public Member getMemberByUuid(UUID memberUuid) {
        return memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public MemberInfoDto getCurrentMemberInfo() throws BusinessException {
        Member member = getCurrentMember();
        return MemberInfoDto.builder()
                .memberUuid(member.getUuid())
                .username(member.getUsername())
                .birthDay(member.getBirthDay())
                .build();
    }



}
