package cc.happybday.fanfare.common.exception.handler;

import cc.happybday.fanfare.common.exception.InvalidValueException;
import cc.happybday.fanfare.common.exception.MemberNotFoundException;
import cc.happybday.fanfare.common.exception.MessageNotFoundException;
import cc.happybday.fanfare.common.response.ErrorResponse;
import cc.happybday.fanfare.common.response.ErrorResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, ErrorResponseCode errorResponseCode) {
        log.error("[ERROR:{}] : {}", errorResponseCode.name(), e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse(errorResponseCode);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponseCode.getStatus()));
    }

    @ExceptionHandler(value = InvalidValueException.class)
    public ResponseEntity<ErrorResponse> handleInvalidValueException(InvalidValueException e) {
        return buildErrorResponse(e, e.getErrorResponseCode());
    }

    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        return buildErrorResponse(e, e.getErrorResponseCode());
    }

    @ExceptionHandler(value = MessageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException e) {
        return buildErrorResponse(e, e.getErrorResponseCode());
    }


}
