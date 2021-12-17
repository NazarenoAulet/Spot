package com.quasartec.spot.models;

import java.util.ArrayList;

public class UserModel {
    public String userId;
    public String userName;
    public String thumbnailPicURL;

    public String city;
    public String description;
    public String profilePicURL;
    public boolean showGroups;
    public boolean showSpots;
public boolean request;//
    ArrayList<String> seenSpots;
    ArrayList<String> blockedUsers;
    ArrayList<String> hiddenSpots;
    ArrayList<String> hiddenGroups;

    public ArrayList<String> getSeenSpots() {
        return seenSpots;
    }

    public ArrayList<String> getBlockedUsers() {
        return blockedUsers;
    }

    public ArrayList<String> getHiddenSpots() {
        return hiddenSpots;
    }

    public ArrayList<String> getHiddenGroups() {
        return hiddenGroups;
    }

    public UserModel() {
    }

    public UserModel(String userId, String userName, String thumbnailPicURL) {
        this.userId = userId;
        this.userName = userName;
        this.thumbnailPicURL = thumbnailPicURL;
    }
    public UserModel(String userId, String userName, String thumbnailPicURL, String profilePicURL,String description, String city,boolean showSpots,boolean showGroups, boolean loggedUser) {
        this.userId = userId;
        this.userName = userName;
        this.thumbnailPicURL = thumbnailPicURL;
        seenSpots=new ArrayList<>();
        hiddenSpots=new ArrayList<>();
        hiddenGroups=new ArrayList<>();

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThumbnailPicURL() {
        return thumbnailPicURL;
    }

    public void setThumbnailPicURL(String thumbnailPicURL) {
        this.thumbnailPicURL = thumbnailPicURL;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public boolean isShowGroups() {
        return showGroups;
    }

    public void setShowGroups(boolean showGroups) {
        this.showGroups = showGroups;
    }

    public boolean isShowSpots() {
        return showSpots;
    }

    public void setShowSpots(boolean showSpots) {
        this.showSpots = showSpots;
    }

}
