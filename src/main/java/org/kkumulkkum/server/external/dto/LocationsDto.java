package org.kkumulkkum.server.external.dto;

public record LocationsDto(
        double x,
        double y,
        String location,
        String address,
        String roadAddress
) {

}