package com.proiectfinal.controller;

import com.proiectfinal.entities.users.UserModel;
import com.proiectfinal.entities.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class DataTransferController {
    private UserService userService;

    @Autowired
    public DataTransferController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional existing = userService.getByUsername(auth.getName());
        if (existing.isPresent()) {
            UserModel userModel = (UserModel) existing.get();
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            try {
                if (userModel.getInfo().getImage() != null) {
                    response.getOutputStream().write(userModel.getInfo().getImage());
                    response.getOutputStream().close();
                }else{
                    response.getOutputStream().write(Files.readAllBytes(Paths.get("src/main/resources/images/emptyProfilePic.png")));
                    response.getOutputStream().close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/getBandAvatar/{id}")
    public void getBandAvatar(@PathVariable("id") Long id, HttpServletResponse response) {

        Optional<UserModel> existing = userService.getById(id);
        System.out.println("A intrat!");
        if(existing.isPresent()){
            try{
                if (existing.get().getInfo().getImage() != null) {
                    response.getOutputStream().write(existing.get().getInfo().getImage());
                    response.getOutputStream().close();
                }else{
                    response.getOutputStream().write(Files.readAllBytes(Paths.get("src/main/resources/images/emptyProfilePic.png")));
                    response.getOutputStream().close();
                }}
            catch ( IOException e){ e.printStackTrace();}
        }
    }
}
