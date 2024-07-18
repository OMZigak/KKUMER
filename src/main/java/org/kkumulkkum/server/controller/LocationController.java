package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.external.NaverService;
import org.kkumulkkum.server.external.dto.LocationsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final NaverService naverService;

    @GetMapping("/v1/locations")
    public ResponseEntity<LocationsDto> getLocations(
            @RequestParam(name="q") final String query
    ) {
        return ResponseEntity.ok(naverService.getLocations(query));
    }

}
