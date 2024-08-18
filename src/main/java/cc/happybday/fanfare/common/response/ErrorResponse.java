package cc.happybday.fanfare.common.response;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;
    private final String code;

    public ErrorResponse(ErrorResponseCode errorResponseCode) {
        this.status = errorResponseCode.getStatus();
        this.message = errorResponseCode.getMessage();
        this.code = errorResponseCode.getCode();
    }


}
