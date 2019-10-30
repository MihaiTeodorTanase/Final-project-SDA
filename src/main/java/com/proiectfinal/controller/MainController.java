package com.proiectfinal.controller;


import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.Info;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {


    private UserService userService;


    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String root() {

        return "index";
    }

    @GetMapping("/events")
    public String evenimente() {
        return "evenimente";
    }

    @GetMapping("/bands")
    public String bands(Model model) {
        List<UserModel> allbands = new ArrayList<>();
        List<UserModel> allUsers = new ArrayList<>();
        List<UserModel> displayBand = new ArrayList<>();
        allUsers = userService.findAll();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getRole().equals(Role.Trupa)) {
                allbands.add(allUsers.get(i));
            }
        }
        for (int i = 0; i < allbands.size(); i++) {
            if (allbands.get(i).getInfo().getBandName() != null && allbands.get(i).getInfo().getCity() != null && allbands.get(i).getInfo().getNoMembers() != 0) {
                displayBand.add(allbands.get(i));
            }
        }
        model.addAttribute("singleBands", displayBand);
        return "bands";
    }

    @GetMapping("/clubs/PubRock")
    public String PubRock() {
        return "pubRock";
    }


    @GetMapping("/bands/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional existing = userService.getById(id);
        if (existing.isPresent()){
            model.addAttribute("single", existing.get());
            return "singleBand";
        }else{
            return "bands";
        }
    }

}


