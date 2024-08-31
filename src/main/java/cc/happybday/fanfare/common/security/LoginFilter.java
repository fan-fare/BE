package cc.happybday.fanfare.common.security;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.common.response.CustomErrorResponder;
import cc.happybday.fanfare.common.response.ErrorResponseCode;
import cc.happybday.fanfare.domain.Role;
import cc.happybday.fanfare.dto.member.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.*;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    // 검증을 담당하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // JSON 요청인지 확인
        if (!"application/json".equals(request.getContentType())) {
            CustomErrorResponder.sendErrorResponse(response, ErrorResponseCode.INVALID_INPUT_VALUE);
        }

        try {
            // ObjectMapper를 사용해 JSON 데이터를 Java 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            // LoginDTO는 요청을 담을 DTO 클래스
            LoginDto loginDto = objectMapper.readValue(messageBody, LoginDto.class);

            // username과 password를 추출하여 UsernamePasswordAuthenticationToken 생성
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

            // 인증 매니저에게 전달하여 인증 시도
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    // 로그인 성공 시 자동 호출되어 실행되는 메서드
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        try {
            String username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

            GrantedAuthority auth = iterator.next();
            Role role = Role.valueOf(auth.getAuthority());

            String token = jwtUtil.createJwt(username, role, 60 * 60 * 10L); // 유효시간은 10시간으로 지정

            response.addHeader("Authorization", "Bearer " + token);
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\":\"로그인에 성공했습니다.\"}");

        } catch (Exception e) {
            CustomErrorResponder.sendErrorResponse(response, INVALID_INPUT_VALUE);
        }
    }


    // 로그인 실패 시 자동 호출되어 실행되는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        CustomErrorResponder.sendErrorResponse(response, ErrorResponseCode.UNAUTHORIZED_MEMBER);
    }

}