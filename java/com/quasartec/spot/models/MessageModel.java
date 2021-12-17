package com.quasartec.spot.models;

public class MessageModel {
    public boolean sent;
    public String text;

    public MessageModel(String text2, boolean sent2) {
        this.sent = sent2;
        this.text = text2;
    }
}
