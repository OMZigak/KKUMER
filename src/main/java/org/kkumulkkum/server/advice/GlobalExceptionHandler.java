package org.kkumulkkum.server.advice;

import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.exception.*;
import org.kkumulkkum.server.exception.code.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<UserErrorCode> handleMeetingException(UserException e) {
        log.warn("GlobalExceptionHandler catch UserException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {MeetingException.class})
    public ResponseEntity<MeetingErrorCode> handleMeetingException(MeetingException e) {
        log.warn("GlobalExceptionHandler catch MeetingException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<AuthErrorCode> handleAuthException(AuthException e) {
        log.warn("GlobalExceptionHandler catch AuthException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {ParticipantException.class})
    public ResponseEntity<ParticipantErrorCode> handleParticipantException(ParticipantException e) {
        log.warn("GlobalExceptionHandler catch ParticipantException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {PromiseException.class})
    public ResponseEntity<PromiseErrorCode> handlePromiseException(PromiseException e) {
        log.warn("GlobalExceptionHandler catch PromiseException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {MemberException.class})
    public ResponseEntity<MemberErrorCode> handleMemberException(MemberException e) {
        log.warn("GlobalExceptionHandler catch MemberException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {AwsException.class})
    public ResponseEntity<AwsErrorCode> handleAwsException(AwsException e) {
        log.warn("GlobalExceptionHandler catch AwsException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    @ExceptionHandler(value = {FirebaseException.class})
    public ResponseEntity<FirebaseErrorCode> handleAwsException(FirebaseException e) {
        log.warn("GlobalExceptionHandler catch FirebaseException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    // 도메인 관련된 에러가 아닐 경우
    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<BusinessErrorCode> handleBusinessException(BusinessException e) {
        log.warn("GlobalExceptionHandler catch BusinessException : {}", e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode());
    }

    // 존재하지 않는 요청에 대한 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<BusinessErrorCode> handleNoPageFoundException(NoHandlerFoundException e) {
        log.warn("GlobalExceptionHandler catch NoHandlerFoundException : {}", BusinessErrorCode.NOT_FOUND_END_POINT.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.NOT_FOUND_END_POINT.getHttpStatus())
                .body(BusinessErrorCode.NOT_FOUND_END_POINT);
    }

    // 잘못된 Method로 요청한 경우
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BusinessErrorCode> handleNoPageFoundException(HttpRequestMethodNotSupportedException e) {
        log.warn("GlobalExceptionHandler catch NoHandlerFoundException : {}", BusinessErrorCode.NOT_FOUND_END_POINT.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.METHOD_NOT_ALLOWED.getHttpStatus())
                .body(BusinessErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<BusinessErrorCode> handleValidationException(Exception e) {
        log.warn("GlobalExceptionHandler catch MethodArgumentNotValidException : {}", e.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.INVALID_ARGUMENTS.getHttpStatus())
                .body(BusinessErrorCode.INVALID_ARGUMENTS);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<BusinessErrorCode> handleMissingParameterException(MissingServletRequestParameterException e) {
        log.warn("GlobalExceptionHandler catch MissingServletRequestParameterException : {}", e.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.MISSING_REQUIRED_PARAM.getHttpStatus())
                .body(BusinessErrorCode.MISSING_REQUIRED_PARAM);
    }

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    public ResponseEntity<BusinessErrorCode> handleMissingHeaderException(MissingRequestHeaderException e) {
        log.warn("GlobalExceptionHandler catch MissingRequestHeaderException : {}", e.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.MISSING_REQUIRED_HEADER.getHttpStatus())
                .body(BusinessErrorCode.MISSING_REQUIRED_HEADER);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<BusinessErrorCode> handleMaxSizeException(MaxUploadSizeExceededException e) {
        log.warn("GlobalExceptionHandler catch MaxUploadSizeExceededException : {}", e.getMessage());
        return ResponseEntity
                .status(BusinessErrorCode.PAYLOAD_TOO_LARGE.getHttpStatus())
                .body(BusinessErrorCode.PAYLOAD_TOO_LARGE);
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BusinessErrorCode> handleException(Exception e) {
        log.error("GlobalExceptionHandler catch Exception : {}", e.getMessage(), e);
        return ResponseEntity
                .status(BusinessErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(BusinessErrorCode.INTERNAL_SERVER_ERROR);
    }
}
