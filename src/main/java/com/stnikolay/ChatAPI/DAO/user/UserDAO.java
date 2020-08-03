package com.stnikolay.ChatAPI.DAO.user;

import com.stnikolay.ChatAPI.model.User;

public interface UserDAO {

    User registration(String username, String password);

    User findUserByUsername(String username);

    User findById(Long id);

}
