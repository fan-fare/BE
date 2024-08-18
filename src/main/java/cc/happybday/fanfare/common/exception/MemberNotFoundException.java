package cc.happybday.fanfare.common.exception;

import cc.happybday.fanfare.common.response.ErrorResponseCode;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super(ErrorResponseCode.ENTITY_NOT_FOUND);
    }
}
