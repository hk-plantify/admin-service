package com.plantify.admin.controller;

import com.plantify.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final UserService userService;

    @GetMapping
    public String health() {
        return "OK";
    }

    @GetMapping("/search")
    public ResponseEntity<Long> getUserId(@RequestParam String username) {
        Long userId = userService.getUserId(username);
        return ResponseEntity.ok(userId);
    }
}
