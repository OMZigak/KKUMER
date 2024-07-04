package org.kkumulkkum.server.controller;

import org.kkumulkkum.server.dto.test.TestDto;
import org.kkumulkkum.server.exception.BusinessException;
import org.kkumulkkum.server.exception.code.BusinessErrorCode;
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

    @GetMapping("/test/default")
    public ResponseEntity<Void> testDefault() {
        throw new RuntimeException();
    }

    @GetMapping("/test/business")
    public ResponseEntity<Void> testBusiness() {
        throw new BusinessException(BusinessErrorCode.BAD_REQUEST);
    }
}
