package com.proiectfinal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }


    @GetMapping("/formBand")
    public String formBand(){
        return "registerBand";
    }


    @GetMapping("/formSpectator")
    public String formSpectator(){
        return "registerSpectator";
    }


    @GetMapping("/formLogin")
    public String formLogIn(){
        return "LogIn";
    }


}


