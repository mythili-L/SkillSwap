package com.studyvalllet.studyvallet.service;

import com.studyvalllet.studyvallet.model.User;
import com.studyvalllet.studyvallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // LOGIN
    public String loginUser(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return "Login Successful";
            } else {
                return "Wrong Password";
            }
        } else {
            return "User Not Found";
        }
    }
}