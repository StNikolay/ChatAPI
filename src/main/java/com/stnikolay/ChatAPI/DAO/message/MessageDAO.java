package com.stnikolay.ChatAPI.DAO.message;

import com.stnikolay.ChatAPI.model.Dialogue;
import com.stnikolay.ChatAPI.model.Message;

import java.util.List;

public interface MessageDAO {

    Message sendMessage(Message message);

    Dialogue getDialogueMessages(String user, String interlocutor, String date);

}
