package com.plantify.admin.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    public String health() {
        return "OK";
    }
}
