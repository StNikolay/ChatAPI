package com.stnikolay.ChatAPI.controller;

import com.stnikolay.ChatAPI.DAO.user.UserDAO;
import com.stnikolay.ChatAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/add")
    public User registration(@RequestBody User user) {
        return userDAO.registration(user.getUsername(), user.getPassword());
    }

    @GetMapping("/")
    public User findUserTestMethod(@RequestParam(defaultValue = "test") String user) {
        return userDAO.findUserByUsername(user);
    }
}