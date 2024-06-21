package com.study4.project2024.service;

import com.study4.project2024.entity.User;
import com.study4.project2024.repository.UserRepository;
import com.study4.project2024.service.imp.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UsersServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(String username, String password, String role) {

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoleName(role);
            userRepository.save(user);
            System.out.println(user);

            return true;
        } catch (Exception e) {
            System.out.println("Error addUser service" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return passwordEncoder.matches(password, userRepository.findByUsername(username).getPassword());
    }

}