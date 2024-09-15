package cc.happybday.fanfare.dto.security;

import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticatedMemberDto {
    Long memberId;
    String username;
    String password;
    Role role;

    public static AuthenticatedMemberDto memberToDto(Member member) {
        return AuthenticatedMemberDto.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }
}
