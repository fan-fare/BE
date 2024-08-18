package cc.happybday.fanfare.common.exception;

import cc.happybday.fanfare.common.response.ErrorResponseCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException() {
        super(ErrorResponseCode.INVALID_INPUT_VALUE);
    }
}