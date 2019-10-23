package com.proiectfinal.controller;

import com.proiectfinal.entities.users.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String root() {

        return "login";
    }


}
