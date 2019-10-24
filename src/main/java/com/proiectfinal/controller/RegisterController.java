package com.proiectfinal.controller;

import com.proiectfinal.config.SpringSecurityConfiguration;
import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.Info;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


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
        model.addAttribute("user", new UserModel());

        return "register";
    }


    @PostMapping("/registerForm")
    public String addUser(@ModelAttribute("user") @Valid UserModel user, BindingResult result, @RequestParam("exampleRadios") String role) {

        Optional existingUsername = userService.getByUsername(user.getUsername());
        Optional existingEmail = userService.getByEmail(user.getEmail());
        if(existingUsername.isPresent()){
            result.rejectValue("username",null,"Username already in use!");
        }
        if(existingEmail.isPresent()){
            result.rejectValue("email",null,"Email address already in use!");
        }
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


    @GetMapping("/editProfile")
    public String myband(Model model){
        model.addAttribute("userInfo",new Info());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user=(User)auth.getPrincipal();
        Optional<UserModel> userModel=userService.findByUsername(user.getUsername());
        model.addAttribute("user",userModel.get().getInfo());
        return "editProfile";
    }


    @GetMapping("/profile")
    public String editProfile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user=(User)auth.getPrincipal();
        Optional<UserModel> userModel=userService.findByUsername(user.getUsername());
        model.addAttribute("user",userModel.get().getInfo());


        return "profile";
    }


    @PostMapping("/editProfile")
    public String addUserInfo(@Valid Info info, BindingResult result){
        if(result.hasErrors()){
            return "editProfile";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.update(auth.getName(),info);

        System.out.println("Info added");
        return "redirect:profile";
    }







}
