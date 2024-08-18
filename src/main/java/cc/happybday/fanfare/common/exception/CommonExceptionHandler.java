package cc.happybday.fanfare.common.exception;

import cc.happybday.fanfare.common.exception.BusinessException;
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

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponseCode errorResponseCode = e.getErrorResponseCode();
        log.error("[ERROR:{}] : {}", errorResponseCode.name(), e.getMessage(), e);

        ErrorResponse errorResponse = new ErrorResponse(errorResponseCode);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponseCode.getStatus()));
    }

}
