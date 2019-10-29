package com.proiectfinal.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class UserProfileController {
    private UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Optional<UserModel> userModel = userService.getByUsername(user.getUsername());
        model.addAttribute("user", userModel.get().getInfo());

        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        model.addAttribute("userInfo", new Info());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Optional<UserModel> userModel = userService.getByUsername(user.getUsername());
        model.addAttribute("user", userModel.get().getInfo());

        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String addUserInfo(@RequestParam("avatar") MultipartFile file, @Valid Info info, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:profile";
        }
        try {
            byte[] image = file.getBytes();
            info.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.update(auth.getName(), info);

        return "redirect:profile";
    }
}
