package com.stnikolay.ChatAPI.controller;

import com.stnikolay.ChatAPI.DAO.message.MessageDAO;
import com.stnikolay.ChatAPI.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageDAO messageDAO;

    @PostMapping("/send")
    public Message send(@RequestBody Message message) {
        return messageDAO.sendMessage(message);
    }
}
