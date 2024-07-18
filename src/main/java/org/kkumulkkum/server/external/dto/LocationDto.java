package org.kkumulkkum.server.external.dto;

public record LocationDto(

        double x,
        double y,
        String location,
        String address,
        String roadAddress
) {

}