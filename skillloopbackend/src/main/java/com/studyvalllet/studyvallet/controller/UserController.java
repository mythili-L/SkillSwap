package com.studyvalllet.studyvallet.controller;

import com.studyvalllet.studyvallet.model.User;
import com.studyvalllet.studyvallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // LOGIN API
    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return userService.loginUser(email, password);
    }
}