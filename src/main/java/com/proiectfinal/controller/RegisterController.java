package com.proiectfinal.controller;

import com.proiectfinal.config.Role;
import com.proiectfinal.config.SpringSecurityConfiguration;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Controller
public class RegisterController {


    private SpringSecurityConfiguration springSecurityConfiguration;


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
    public String addUserInfo(@RequestParam("avatar")MultipartFile file,@Valid Info info, BindingResult result){
        if(result.hasErrors()){
            return "redirect:profile";
        }
        try {
            byte[] image = file.getBytes();
            info.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.update(auth.getName(),info);

        System.out.println("Info added");
        return "redirect:profile";
    }


    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional existing = userService.getByUsername(auth.getName());
        if (existing.isPresent()) {
            UserModel userModel = (UserModel) existing.get();
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            try {
                response.getOutputStream().write(userModel.getInfo().getImage());
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
