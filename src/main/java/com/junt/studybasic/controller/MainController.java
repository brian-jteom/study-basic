package com.junt.studybasic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/")
    public String welcome() {
        return "WELCOME TO API";
    }

    @GetMapping(value = "/health/checker")
    public String healthChecker() {
        return "Success";
    }
}
