package org.kkumulkkum.server.dto.test;

public record TestDto(
        String test
) {
    public static TestDto from(String test) {
        return new TestDto(test);
    }
}
