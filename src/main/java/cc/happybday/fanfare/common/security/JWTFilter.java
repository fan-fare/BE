package cc.happybday.fanfare.common.security;

import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Role;
import cc.happybday.fanfare.dto.security.AuthenticatedMemberDto;
import cc.happybday.fanfare.dto.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // jwt 토큰 추출
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response); // 다음 필터로 요청, 응답 전달
            return;
        }

        // Bearer 제거 후 순수 토큰 추출
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            System.out.printf("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 usename 과 role 추출
        String username = jwtUtil.getUsername(token);
        Role role = Role.valueOf(jwtUtil.getRole(token).toUpperCase());

        // 추출한 정보로 Member 엔티티 생성
        AuthenticatedMemberDto memberDto = AuthenticatedMemberDto.builder()
                .username(username)
                .password("temp")
                .role(role)
                .build();

        // 시큐리티 인증 토큰 객체 생성
        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 임시 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 필터로 요청, 응답 전달
        filterChain.doFilter(request, response);
    }
}
