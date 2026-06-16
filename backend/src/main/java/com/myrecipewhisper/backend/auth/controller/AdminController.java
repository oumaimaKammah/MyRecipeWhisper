package com.myrecipewhisper.backend.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String adminTest() {
        return "Admin test successful!";
    }

}
