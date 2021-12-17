package com.quasartec.spot.models;

public class ChatCardModel {
    public String lastMessage;
    public String userId;
    public String userName;

    public ChatCardModel(String message, String userid, String userName2) {
        this.lastMessage = message;
        this.userId = userid;
        this.userName = userName2;
    }
}
