package com.project.springreact.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/" )
public class Test1Controller {
    @GetMapping("/test")
    public String hello() {
        return "hello World!";
    }
}
