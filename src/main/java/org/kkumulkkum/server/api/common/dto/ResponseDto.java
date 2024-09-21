package org.kkumulkkum.server.api.common.dto;

import org.kkumulkkum.server.common.exception.code.DefaultErrorCode;

public record ResponseDto<T> (
        boolean success,
        T data,
        ErrorDto error
) {
    public static <T> ResponseDto<T> success(final T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(DefaultErrorCode error) {
        return new ResponseDto<>(false, null, ErrorDto.of(error.getCode(), error.getMessage()));
    }
}
