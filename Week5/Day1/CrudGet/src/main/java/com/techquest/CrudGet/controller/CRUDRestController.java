package com.techquest.CrudGet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUDRestController {
    
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}