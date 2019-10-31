package com.proiectfinal.controller;

import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerForm")
    public String formSpectator(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }


    @PostMapping("/registerForm")
    public String addUser(@ModelAttribute("user") @Valid UserModel user, BindingResult result, @RequestParam("exampleRadios") String role) {
        Optional existingUsername = userService.getByUsername(user.getUsername().trim());
        Optional existingEmail = userService.getByEmail(user.getEmail().trim());

        if (existingUsername.isPresent()) {
            result.rejectValue("username", null, "Username already in use!");
        }
        if (existingEmail.isPresent()) {
            result.rejectValue("email", null, "Email address already in use!");
        }
        if (result.hasErrors()) {
            return "register";
        }
        if (role.equals("option1")) {
            user.setRole(Role.Spectator);
        } else {
            user.setRole(Role.Trupa);
        }
        userService.add(user);
        System.out.println("User " + user.getUsername() + " has been added in db");

        return "index";
    }
}
