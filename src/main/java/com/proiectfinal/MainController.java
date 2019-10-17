package com.proiectfinal;

import com.proiectfinal.entities.UserModel;
import com.proiectfinal.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

UserService userService;

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


    @GetMapping("/evenimente")
        public String evenimente(){
            return "evenimente";
        }

@GetMapping("/trupe")
    public String trupe(){
        return  "trupe";
}


    @PostMapping("/formSpectator")
    public String addUser(@Valid UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.add(user);
        model.addAttribute("users", userService.findAll());
        return "index";

    }






}


