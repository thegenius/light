package com.lvonce.lightserver.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Home {

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "hello world";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}