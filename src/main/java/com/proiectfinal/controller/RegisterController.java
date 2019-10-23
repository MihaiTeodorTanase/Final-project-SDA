package com.proiectfinal.controller;

import com.proiectfinal.config.SpringSecurityConfiguration;
import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.Info;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class RegisterController {


    private SpringSecurityConfiguration springSecurityConfiguration;


    private UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/registerForm")
    public String formSpectator(Model model) {
        model.addAttribute("spectator", new UserModel());

        return "register";
    }


    @PostMapping("/registerForm")
    public String addUser(@Valid UserModel user, BindingResult result,@RequestParam("exampleRadios") String role) {
        if (result.hasErrors()) {
            return "register";
        }
        if(role.equals("option1")){
            user.setRole(Role.Spectator);
        }
        else{
            user.setRole(Role.Trupa);
        }


        userService.add(user);
        System.out.println("User "+user.getUsername()+" has been added in db");

        return "index";

    }


    @GetMapping("/profile")
    public String myband(Model model){
        model.addAttribute("userInfo",new Info());
        return "profile";
    }

    @PostMapping("/profile")
    public String addUserInfo(@Valid Info info, BindingResult result){
        if(result.hasErrors()){
            return "profile";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.update(auth.getName(),info);

        System.out.println("Info added");
        return "index";
    }




}
