package org.kkumulkkum.server.dto.test;

public record TestDto(
        String test
) {
    public static TestDto of (String test) {
        return new TestDto(test);
    }
}
