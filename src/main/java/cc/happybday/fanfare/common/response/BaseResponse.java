package cc.happybday.fanfare.common.response;

import lombok.Getter;
import lombok.ToString;

@Getter
public class BaseResponse<T> {

    private final int status;
    private final String message;
    private final String code;
    private final T data;

    public BaseResponse(T data,BaseResponseCode baseResponseCode) {
        this.status = baseResponseCode.getStatus();
        this.message = baseResponseCode.getMessage();
        this.code = baseResponseCode.getCode();
        this.data = data;
    }

}
