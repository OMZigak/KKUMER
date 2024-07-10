package org.kkumulkkum.server.controller;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.external.NaverService;
import org.kkumulkkum.server.external.dto.LocationsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LocationController {

    private final NaverService naverService;

    @GetMapping("/locations")
    public ResponseEntity<List<LocationsDto>> getLocations(
            @RequestParam(name="q") String query
    ) {
        return ResponseEntity.ok(naverService.getLocations(query));
    }

}