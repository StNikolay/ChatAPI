package com.stnikolay.ChatAPI.controller;

import com.stnikolay.ChatAPI.DAO.user.UserDAO;
import com.stnikolay.ChatAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping
    public User registration(@RequestBody User user) {
        return userDAO.registration(user.getUsername(), user.getPassword());
    }

    @GetMapping("/check")
    public boolean checkForExistence(@RequestParam String username,
                                     @RequestParam String password) {
        return userDAO.checkForExistence(username, password);
    }

    @GetMapping
    public User findUserTestMethod(@RequestParam(defaultValue = "test") String user) {
        return userDAO.findUserByUsername(user);
    }
}