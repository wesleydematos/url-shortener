package com.shortener.urlshortner.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    
    @GetMapping(value="/")
    public String getHelloWorldString() {
        return "olá mundo";
    }
    
}
