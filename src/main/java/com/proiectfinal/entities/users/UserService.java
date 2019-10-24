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
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    public UserModel add(UserModel userModel) {
        String pass = userModel.getPassword();
        String cript = bCryptPasswordEncoder.encode(pass);
        userModel.setPassword(cript);
        userModel.setInfo(new Info());
        return userRepository.saveAndFlush(userModel);
    }

    public Optional<UserModel> update(String username,Info newInfo){
        Optional<UserModel> existingClassroommodel = userRepository.findByUsername(username);
        if(existingClassroommodel.isPresent()){
            UserModel userModel = existingClassroommodel.get();
            Info oldInfo = userModel.getInfo();
            if(newInfo.getBandName() == null ||newInfo.getBandName().isEmpty() ){
               newInfo.setBandName(oldInfo.getBandName());
            }
            if(newInfo.getCity() == null || newInfo.getCity().isEmpty()){
                newInfo.setCity(oldInfo.getCity());
            }
            if(newInfo.getFirst_name() == null || newInfo.getFirst_name().isEmpty()){
                newInfo.setFirst_name(oldInfo.getFirst_name());
            }
            if(newInfo.getName() == null || newInfo.getName().isEmpty()){
                newInfo.setName(oldInfo.getName());
            }
            if(newInfo.getNoConcerts()==0){
                newInfo.setNoConcerts(oldInfo.getNoConcerts());
            }
            if(newInfo.getNoMembers()==0 ){
                newInfo.setNoMembers(oldInfo.getNoMembers());
            }
            userModel.setInfo(newInfo);
            return Optional.of(userRepository.saveAndFlush(userModel));
        }
        return Optional.empty();
    }
    public Optional<UserModel> getByUsername(String username){
        Optional<UserModel> existing = userRepository.findByUsername(username);
        if(existing.isPresent()){
            return userRepository.findByUsername(username);
        }
        return Optional.empty();
    }

    public Optional<UserModel> getByEmail(String email){
        Optional<UserModel> existing = userRepository.findByEmail(email);
        if(existing.isPresent()){
            return userRepository.findByEmail(email);
        }
        return Optional.empty();
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }
        UserModel userModel = byUsername.get();
        return new User(userModel.getUsername(), userModel.getPassword(), getAuthority(userModel));
    }

    private List getAuthority(UserModel userModel) {

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userModel.getRole())); //TODO:replace user role by userModel.getRole(),can be user,admin ...
    }



}
