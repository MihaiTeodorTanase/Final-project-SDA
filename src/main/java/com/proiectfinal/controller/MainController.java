package com.proiectfinal.controller;


import com.proiectfinal.entities.UserModel;
import com.proiectfinal.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {

        return "index";
    }


    @GetMapping("/formBand")
    public String formBand() {
        return "registerBand";
    }




//    @GetMapping("/login")
//    public String formLogIn() {
//        return "test";
//    }


    @GetMapping("/evenimente")
    public String evenimente() {
        return "evenimente";
    }

    @GetMapping("/trupe")
    public String trupe() {
        return "trupe";
    }






}


