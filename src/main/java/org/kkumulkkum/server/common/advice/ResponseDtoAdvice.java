package org.kkumulkkum.server.common.advice;

import org.kkumulkkum.server.api.common.dto.ResponseDto;
import org.kkumulkkum.server.common.exception.code.DefaultErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// TODO: 구현을 숨기고 이해를 어렵게한다는 이유로 곧 disable될 예정
@RestControllerAdvice(basePackages = "org.kkumulkkum.server")
public class ResponseDtoAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !(returnType.getParameterType() == ResponseDto.class)
                && MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (body instanceof DefaultErrorCode)
            return ResponseDto.fail((DefaultErrorCode) body);
        return ResponseDto.success(body);
    }
}
