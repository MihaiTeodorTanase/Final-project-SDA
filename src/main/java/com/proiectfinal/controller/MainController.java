package com.proiectfinal.controller;


import com.proiectfinal.config.Role;
import com.proiectfinal.entities.users.Info;
import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {

        return "index";
    }

    @GetMapping("/events")
    public String evenimente() {
        return "evenimente";
    }

    @GetMapping("/bands")
    public String trupe(Model model) {
        List<UserModel> allbands = new ArrayList<>();
        List<UserModel> allUsers = new ArrayList<>();
        List<Info> displayBand=new ArrayList<>();

        allUsers = userService.findAll();

        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getRole().equals(Role.Trupa)) {
                allbands.add(allUsers.get(i));
            }
        }

        for(int i=0;i<allbands.size();i++){
            if(allbands.get(i).getInfo().getBandName()!=null && allbands.get(i).getInfo().getCity()!=null && allbands.get(i).getInfo().getNoMembers()!=0){
                displayBand.add(allbands.get(i).getInfo());
            }
        }


        for(Info u:displayBand) {
            System.out.println(u.getBandName());


        }

        model.addAttribute("bands",displayBand);

        return "trupe";
    }

    @GetMapping("/clubs")
    public String clubs() {
        return "clubs";
    }

    @GetMapping("/clubs/PubRock")
    public String PubRock() {
        return "PubRock";
    }


}


