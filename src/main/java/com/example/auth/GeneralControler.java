package com.example.auth;

import org.springframework.web.bind.annotation.GetMapping;

public class GeneralControler {
    @GetMapping("/")
    public String getHomePage(){
        return "HomePage.html";
    }
}
