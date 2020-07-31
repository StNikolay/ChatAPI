package com.stnikolay.ChatAPI.DAO.message;

import com.stnikolay.ChatAPI.model.Message;

public interface MessageDAO {

    Message sendMessage(Message message);

}
