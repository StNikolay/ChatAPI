package com.stnikolay.ChatAPI.model;

public class Dialogue {

    private User user;
    private User interlocutor;
    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(User interlocutor) {
        this.interlocutor = interlocutor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
