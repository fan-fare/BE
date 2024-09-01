package cc.happybday.fanfare.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorResponseCode {

    // 공통
    INVALID_INPUT_VALUE("F01", "유효하지 않은 값을 입력하였습니다.",HttpStatus.BAD_REQUEST.value()),
    ENTITY_NOT_FOUND("F02", "리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    INTERNAL_SERVER_ERROR("F03", "예기치 못한 서버 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // Member
    DUPLICATE_USERNAME("F04", "이미 존재하는 username입니다.", HttpStatus.BAD_REQUEST.value()),
    MEMBER_NOT_FOUND("F05", "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    MEMBER_ROLE_NOT_FOUND("F06", "회원의 ROLE 값을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    UNAUTHORIZED_MEMBER("F07", "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED.value()),
    FORBIDDEN_ACCESS("F07", "이 리소스에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN.value()),

    // Message
    MESSAGE_NOT_FOUND("F08", "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value());


    private final String code;
    private final String message;
    private final int status;

    ErrorResponseCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
