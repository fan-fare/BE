package cc.happybday.fanfare.common.exception;

import cc.happybday.fanfare.common.response.ErrorResponseCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorResponseCode errorResponseCode;

    public BusinessException(ErrorResponseCode errorResponseCode) {
        super(errorResponseCode.getMessage());
        this.errorResponseCode = errorResponseCode;
    }
}
