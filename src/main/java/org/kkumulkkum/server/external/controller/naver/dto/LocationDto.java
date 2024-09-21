package org.kkumulkkum.server.external.controller.naver.dto;

public record LocationDto(

        double x,
        double y,
        String location,
        String address,
        String roadAddress
) {

}