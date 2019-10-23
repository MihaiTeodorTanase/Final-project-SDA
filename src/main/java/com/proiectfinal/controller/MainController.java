package com.proiectfinal.controller;


import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {

        return "index";
    }

    @GetMapping("/evenimente")
    public String evenimente() {
        return "evenimente";
    }

    @GetMapping("/trupe")
    public String trupe() {
        return "trupe";
    }

    @GetMapping("/clubs")
    public String clubs() {return "clubs";}

    @GetMapping("/clubs/PubRock")
    public String PubRock() {return "PubRock";}

    @GetMapping("/BandProfile")
    public String profileBand(){return "ProfileBand";}



}


