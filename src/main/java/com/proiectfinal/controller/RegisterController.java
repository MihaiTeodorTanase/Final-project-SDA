package com.proiectfinal.controller;

import com.proiectfinal.config.PasswordEncoderProducer;
import com.proiectfinal.config.SpringSecurityConfiguration;
import com.proiectfinal.entities.Role;
import com.proiectfinal.entities.UserModel;
import com.proiectfinal.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;




@Controller
public class RegisterController {


    private SpringSecurityConfiguration springSecurityConfiguration;

    @Autowired
    private UserService userService;

    @GetMapping("/formSpectator")
    public String formSpectator(Model model) {
        model.addAttribute("spectator", new UserModel());

        return "registerSpectator";
    }


    @PostMapping("/formSpectator")
    public String addUser(@Valid UserModel user, BindingResult result) {
        if (result.hasErrors()) {
            return "formSpectator";
        }
        user.setRole(Role.Spectator);

        userService.add(user);
        System.out.println("User "+user.getUsername()+" has been added in db");

        return "index";

    }
}
