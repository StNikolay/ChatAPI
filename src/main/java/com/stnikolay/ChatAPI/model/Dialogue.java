package com.stnikolay.ChatAPI.model;

import java.util.List;

public class Dialogue {

    private List<Message> messages;

    public Dialogue() {
    }

    public Dialogue(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
