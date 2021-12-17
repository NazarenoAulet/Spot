package com.quasartec.spot.models;

import java.util.Date;

public class CommentModel {
    Long date;
    String text;
    String userId;
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    String userPicURL;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPicURL() {
        return userPicURL;
    }

    public void setUserPicURL(String userPicURL) {
        this.userPicURL = userPicURL;
    }

    public boolean isMyComment() {
        return myComment;
    }

    public void setMyComment(boolean myComment) {
        this.myComment = myComment;
    }

    boolean myComment;
    public CommentModel(String userId2, String userPicURL2, String text2, Long date ,boolean myComment, String userName) {
        this.userId = userId2;
        this.userPicURL = userPicURL2;
        this.text = text2;
        this.date = date;
this.userName=userName;
        this.myComment=myComment;
    }
}
