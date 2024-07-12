package org.kkumulkkum.server.advice;

import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.exception.*;
import org.kkumulkkum.server.exception.code.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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

    @ExceptionHandler(value = {ParticipantException.class})
    public ResponseEntity<ParticipantErrorCode> handleParticipantException(ParticipantException e) {
        log.error("GlobalExceptionHandler catch ParticipantException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {PromiseException.class})
    public ResponseEntity<PromiseErrorCode> handlePromiseException(PromiseException e) {
        log.error("GlobalExceptionHandler catch PromiseException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {MemberException.class})
    public ResponseEntity<MemberErrorCode> handleMemberException(MemberException e) {
        log.error("GlobalExceptionHandler catch MemberException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {AwsException.class})
    public ResponseEntity<AwsErrorCode> handleAwsException(AwsException e) {
        log.error("GlobalExceptionHandler catch AwsException : {}", e.getErrorCode().getMessage());
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

    @ExceptionHandler(value = {HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<BusinessErrorCode> handleValidationException(Exception e) {
        log.error("GlobalExceptionHandler catch MethodArgumentNotValidException : {}", e.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.INVALID_ARGUMENTS.getHttpStatus())
                .body(BusinessErrorCode.INVALID_ARGUMENTS);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<BusinessErrorCode> handleMaxSizeException(MaxUploadSizeExceededException e) {
        log.error("GlobalExceptionHandler catch MaxUploadSizeExceededException : {}", e.getMessage());
        e.printStackTrace();
        return ResponseEntity
                .status(BusinessErrorCode.PAYLOAD_TOO_LARGE.getHttpStatus())
                .body(BusinessErrorCode.PAYLOAD_TOO_LARGE);
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
