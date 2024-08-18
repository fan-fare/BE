package cc.happybday.fanfare.common.exception;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.common.response.ErrorResponseCode;

public class MessageNotFoundException extends BusinessException {

    public MessageNotFoundException() {
        super(ErrorResponseCode.ENTITY_NOT_FOUND);
    }
}
