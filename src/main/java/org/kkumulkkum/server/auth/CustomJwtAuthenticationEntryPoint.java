package org.kkumulkkum.server.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.constant.Constant;
import org.kkumulkkum.server.dto.common.ResponseDto;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.kkumulkkum.server.exception.code.DefaultErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        handleException(request, response);
    }

    private void handleException(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        setResponse(request, response);
    }

    private void setResponse(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        DefaultErrorCode errorCode = (DefaultErrorCode) request.getAttribute("exception");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(Constant.CHARACTER_ENCODING_UTF8);
        if (errorCode == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(
                    objectMapper.writeValueAsString(ResponseDto.fail(AuthErrorCode.UNAUTHORIZED)));
        } else {
            response.setStatus(errorCode.getHttpStatus().value());
            response.getWriter().write(
                    objectMapper.writeValueAsString(ResponseDto.fail(errorCode)));
        }
    }
}
