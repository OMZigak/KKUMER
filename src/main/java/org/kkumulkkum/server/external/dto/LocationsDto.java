package org.kkumulkkum.server.external.dto;

import java.util.List;

public record LocationsDto (
        List<LocationDto> locations
) {

        public static LocationsDto of(List<LocationDto> locations) {
            return new LocationsDto(locations);
        }

}
