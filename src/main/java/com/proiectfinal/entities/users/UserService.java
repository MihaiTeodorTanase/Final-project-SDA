package com.proiectfinal.entities.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public UserModel add(UserModel userModel) {
        String pass=userModel.getPassword();
       String cript= bCryptPasswordEncoder.encode(pass);
        userModel.setPassword(cript);

        return userRepository.saveAndFlush(userModel);


    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> byUsername=userRepository.findByUsername(username);
        if(!byUsername.isPresent()){
            throw  new UsernameNotFoundException("user not found");
        }
       UserModel userModel=byUsername.get();
        return new User(userModel.getUsername(),userModel.getPassword(),getAuthority(userModel));
    }

    private List getAuthority(UserModel userModel) {

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+userModel.getRole())); //TODO:replace user role by userModel.getRole(),can be user,admin ...
    }







}
