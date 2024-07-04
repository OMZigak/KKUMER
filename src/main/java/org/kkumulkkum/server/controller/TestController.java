package org.kkumulkkum.server.controller;

import org.kkumulkkum.server.dto.test.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test/void")
    public ResponseEntity<Void> testVoid() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/dto")
    public ResponseEntity<TestDto> testDto() {
        return ResponseEntity.ok(new TestDto("test"));
    }
}
