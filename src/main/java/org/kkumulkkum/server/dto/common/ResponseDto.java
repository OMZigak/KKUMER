package org.kkumulkkum.server.dto.common;

import org.kkumulkkum.server.exception.code.DefaultErrorCode;

public record ResponseDto<T> (
        T data,
        ErrorDto error
) {
    public static <T> ResponseDto<T> success(final T data) {
        return new ResponseDto<>(data, null);
    }

    public static <T> ResponseDto<T> fail(DefaultErrorCode error) {
        return new ResponseDto<>(null, ErrorDto.of(error.getCode(), error.getMessage()));
    }
}
