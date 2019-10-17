package com.proiectfinal.users;

import com.proiectfinal.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public UserModel add(UserModel userModel) {
        return userRepository.saveAndFlush(userModel);
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }
}
