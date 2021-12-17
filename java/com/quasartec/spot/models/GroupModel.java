package com.quasartec.spot.models;

import java.util.Date;

public class GroupModel {
    public boolean admin;
    public String adminId;
    public String adminName;
    public String adminPicURL;
    public boolean belong;
    public Category category;
    public Date creationDate;
    public String description;
    public String groupId;
    public String imageURL;
    public boolean locked;
    public boolean publicG;
    public boolean shown;
    public int spotsAmount;
    public Category subCategory;
    public String title;
    public int usersAmount;

    public GroupModel(String groupId2, String adminId2, String title2, String imageURL2, Category category2, Category subCategory2, Date creationdate, boolean locked2, boolean shown2, boolean publicG2, int usersAmount2, int spotsAmount2) {
        this.groupId = groupId2;
        this.adminId = adminId2;
        this.title = title2;
        this.imageURL = imageURL2;
        this.category = category2;
        this.subCategory = subCategory2;
        this.creationDate = creationdate;
        this.locked = locked2;
        this.shown = shown2;
        this.publicG = publicG2;
        this.usersAmount = usersAmount2;
        this.spotsAmount = spotsAmount2;
    }

    public void setAdmin(String adminName, String adminPicURL){
        this.adminName=adminName;
        this.adminPicURL=adminPicURL;
    }
}
