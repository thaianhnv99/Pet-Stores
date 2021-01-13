package com.myproject.authentic.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/hello")
public class PublicController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
}
