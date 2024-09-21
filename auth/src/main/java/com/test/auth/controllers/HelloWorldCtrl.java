package com.test.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldCtrl {
    @GetMapping("/")
    public String helloWorld(HttpServletRequest request) {
        return "Hello World" + request.getSession().getId();
    }
}
