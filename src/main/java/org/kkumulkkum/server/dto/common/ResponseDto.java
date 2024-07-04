package org.kkumulkkum.server.dto.common;

public record ResponseDto<T> (
        T data,
        ErrorDto error
) {
    public static <T> ResponseDto<T> success(final T data) {
        return new ResponseDto<>(data, null);
    }

    public static <T> ResponseDto<T> fail(ErrorDto error) {
        return new ResponseDto<>(null, ErrorDto.of(error.code(), error.message()));
    }
}
