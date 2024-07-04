package org.kkumulkkum.server.dto.common;

public record ErrorDto(
        int code,
        String message
) {
    public static ErrorDto of(int code, String message) {
        return new ErrorDto(code, message);
    }
}
