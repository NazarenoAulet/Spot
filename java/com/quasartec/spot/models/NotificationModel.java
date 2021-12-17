package com.quasartec.spot.models;

public class NotificationModel {
    public int andXpeople;
    public String group;
    public String profilePicUrl;
    public boolean showPic;
    public String spot;
    public int type;
    public String user;

    public NotificationModel(String user2, String group2, boolean showPic2, int type2, int andXpeople2) {
        this.showPic = showPic2;
        this.user = user2;
        this.group = group2;
        this.type = type2;
        this.andXpeople = andXpeople2;
    }

    public NotificationModel(String user2, String group2, boolean showPic2, int type2, String spot2) {
        this.showPic = showPic2;
        this.user = user2;
        this.group = group2;
        this.type = type2;
        this.spot = spot2;
    }
}
