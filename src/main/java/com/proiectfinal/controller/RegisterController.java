package com.proiectfinal.controller;

import com.proiectfinal.config.SpringSecurityConfiguration;
import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.Info;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/myband")
    public String myband(Model model){
        model.addAttribute("userInfo",new Info());

        return "ProfileBand";
    }

    @PostMapping("/myband")
    public String addUserInfo(@Valid Info info, BindingResult result){
        if(result.hasErrors()){
            return "ProfileBand";
        }

        userService.saveInfo(info);
        System.out.println("Info added");

        return "index";
    }




}
