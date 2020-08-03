package com.stnikolay.ChatAPI.controller;

import com.stnikolay.ChatAPI.DAO.message.MessageDAO;
import com.stnikolay.ChatAPI.model.Dialogue;
import com.stnikolay.ChatAPI.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageDAO messageDAO;

    @PostMapping("/send")
    public Message send(@RequestBody Message message) {
        return messageDAO.sendMessage(message);
    }

    @GetMapping("/dialogue_for_date")
    public List<Message> messageList(@RequestBody Dialogue dialogue) {
        return messageDAO.getDialogueMessages(dialogue);
    }
}
