package com.lvonce.lightserver.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class User {

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
