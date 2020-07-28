package com.stnikolay.ChatAPI.DAO.user;

import com.stnikolay.ChatAPI.model.User;

public interface UserDAO {

    void registration(String username, String password);

    User findUserByUsername(String username);

}
