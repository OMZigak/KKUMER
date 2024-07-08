package org.kkumulkkum.server.advice;

import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.BusinessException;
import org.kkumulkkum.server.exception.MeetingException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.kkumulkkum.server.exception.code.BusinessErrorCode;
import org.kkumulkkum.server.exception.code.MeetingErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MeetingException.class})
    public ResponseEntity<MeetingErrorCode> handleMeetingException(MeetingException e) {
        log.error("GlobalExceptionHandler catch MeetingException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<AuthErrorCode> handleAuthException(AuthException e) {
        log.error("GlobalExceptionHandler catch AuthException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    // 도메인 관련된 에러가 아닐 경우
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<BusinessErrorCode> handleBusinessException(BusinessException e) {
        log.error("GlobalExceptionHandler catch BusinessException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    // 존재하지 않는 요청에 대한 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BusinessErrorCode> handleNoPageFoundException(Exception e) {
        log.error("GlobalExceptionHandler catch NoHandlerFoundException : {}", BusinessErrorCode.NOT_FOUND_END_POINT.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.NOT_FOUND_END_POINT.getHttpStatus())
                .body(BusinessErrorCode.NOT_FOUND_END_POINT);
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BusinessErrorCode> handleException(Exception e) {
        log.error("GlobalExceptionHandler catch Exception : {}", e.getMessage());
        e.printStackTrace();
        return ResponseEntity
                .status(BusinessErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(BusinessErrorCode.INTERNAL_SERVER_ERROR);
    }
}
