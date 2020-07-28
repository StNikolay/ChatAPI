package com.stnikolay.ChatAPI.controller;

import com.stnikolay.ChatAPI.DAO.user.UserDAO;
import com.stnikolay.ChatAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/add")
    public User registration(@RequestParam String username,
                             @RequestParam String password) {
        userDAO.registration(username, password);
        return userDAO.findUserByUsername(username);
    }

    @GetMapping("/")
    public User findUserTestMethod(@RequestParam(defaultValue = "test") String user) {
        return userDAO.findUserByUsername(user);
    }
}