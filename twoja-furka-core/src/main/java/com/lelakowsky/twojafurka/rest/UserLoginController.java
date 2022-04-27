package com.lelakowsky.twojafurka.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserLoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
