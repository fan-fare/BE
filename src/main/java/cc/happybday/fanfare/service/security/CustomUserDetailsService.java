package cc.happybday.fanfare.service.security;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.dto.security.CustomUserDetails;
import cc.happybday.fanfare.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.MEMBER_NOT_FOUND;
import static cc.happybday.fanfare.dto.security.AuthenticatedMemberDto.memberToDto;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        return new CustomUserDetails(memberToDto(member));
    }

}
