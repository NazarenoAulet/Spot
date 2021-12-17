package com.quasartec.spot.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SpotModel {
  boolean isFavourited;
    boolean inPublicGroup;
    boolean inBelongGroup;
    public String spotId;
    public String userId;
    public String groupId;
    //Geohash
    public String title;
    public Date creationDate;
    public Category subCategory;
    public Category category;
    public boolean indefinite;
    public Date startDate;
    public int favouritesAmmount;
double lat,lon;
    public boolean showsUser;
    public String profilePicURL;
    public boolean insideMyGroup;
    public boolean mine;

    String address;
    public int commentsAmmount;
    public String groupName;
    public String imageURL;
    String userName;
boolean inAdminGroup, inFavourites;
    String direction;
    String description;
    public long durationInMinutes;
    List<CommentModel> comments;

    public SpotModel(){}

    public SpotModel(String userName,String description, String direction, String id,List<Map<String,Object>> commentsMap ){
        address=direction;
        this.description=description;
        this.userName=userName;
        this.spotId=id;

        int len=comments.size();
        for(int i=0;i<len;i++) {
            comments.add((CommentModel) commentsMap.get(i).get(String.valueOf(i)));
        }

    }


    public SpotModel(String title2, String userId2, String groupName2, Date creationdate, String groupId2, String spotId2, String imageURL2, String profilePicURL2, int commentsAmmount2, int savings2, int durationInMinutes2, boolean showsUser2, Date startdate, Category category2, Category subCategory2) {
        this.title = title2;
        this.userId = userId2;
        this.groupName = groupName2;
        this.groupId = groupId2;
        this.spotId = spotId2;
        this.imageURL = imageURL2;
        this.profilePicURL = profilePicURL2;
        this.commentsAmmount = commentsAmmount2;
        this.favouritesAmmount = savings2;
        this.durationInMinutes = durationInMinutes2;
        this.showsUser = showsUser2;
        this.startDate = startdate;
        this.category = category2;
        this.subCategory = subCategory2;
        this.creationDate = creationdate;
    }


    public SpotModel(String title2, String userId2, String groupName2, Date creationdate, String groupId2, String spotId2, String imageURL2, String profilePicURL2, int commentsAmmount2, int savings2, int durationInMinutes2, boolean showsUser2, Date startdate, Category category2, Category subCategory2, String description, String userName) {
        this.title = title2;
        this.userId = userId2;
        this.groupName = groupName2;
        this.groupId = groupId2;
        this.spotId = spotId2;
        this.imageURL = imageURL2;
        this.profilePicURL = profilePicURL2;
        this.commentsAmmount = commentsAmmount2;
        this.favouritesAmmount = savings2;
        this.durationInMinutes = durationInMinutes2;
        this.showsUser = showsUser2;
        this.startDate = startdate;
        this.category = category2;
        this.subCategory = subCategory2;
        this.creationDate = creationdate;

        this.description = description;
        this.userName = userName;
    }

    public SpotModel(String id, String category, String subcategory, int favouritesAmount, int commentsAmount, long creationDate, long startingDate, long durationInMinutes, double lat, double lon, String title, String picURL, String userID, String userPicURL, String groupName, String groupID, boolean mine, boolean inAdminGroup, boolean inBelongGroup, boolean showsUser, boolean isFavourited, boolean inPublicGroup, boolean inFavourites) {
    this.spotId=id;

        switch (category) {
            case "resource":
                this.category = Category.MATERIAL;
                break;
            case "social":
                this.category = Category.SOCIAL;
                break;
            case "enironment":
                this.category = Category.ENVIRONMENT;
                break;
        }


        switch (subcategory) {
            case "buy":
                this.subCategory = Category.M_BUY;
                break;
            case "sell":
                subCategory = Category.M_SELL;
                break;
            case "donation":
                subCategory = Category.M_DONATION;
                break;
            case "recycling":
                subCategory = Category.M_RECYCLING;
                break;

            case "party":
                subCategory = Category.S_PARTY;
                break;
            case "group":
                subCategory = Category.S_GROUP_ACTIVITY;
                break;
            case "humanitarian":
                subCategory = Category.S_HUMANITARIAN_ACTIVITY;
                break;
            case "help":
                subCategory = Category.S_HELP_NEEDED;
                break;

            case "warning":
                subCategory = Category.E_WARNING;
                break;
            case "interest":
                subCategory = Category.E_POINT_OF_INTEREST;
                break;
        }

        this.favouritesAmmount=favouritesAmount;
        this.commentsAmmount=commentsAmount;
       //this.creationDate=creationDate.todate;
        //this.startingDate=creationDate.todate;
        this.durationInMinutes=durationInMinutes;
        this.lat=lat;
        this.lon=lon;
        this.title=title;
        this.imageURL=picURL;
        this.userId=userID;
        this.profilePicURL=userPicURL;
        this.groupName=groupName;
        this.groupId=groupID;
this.mine=mine;
this.inAdminGroup=inAdminGroup;
this.inBelongGroup=inBelongGroup;
this.inFavourites=inFavourites;
this.inPublicGroup=inPublicGroup;
this.showsUser=showsUser;
this.isFavourited=isFavourited;
        //    public SpotModel(String id, String category, String subcategory, int favouritesAmount, int commentsAmount,
        //    Long creationDate, Long startingDate, Long durationInMinutes, Double lat, Double lon, String title, String picURL,
        //    String userID, String userPicURL, String groupName, Boolean mine, Boolean inAdminGroup, Boolean inBelongGroup,
        //    Boolean showsUser, Boolean isFavourited, Boolean inPublicGroup, Boolean inFavourites) {
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isIndefinite() {
        return indefinite;
    }

    public void setIndefinite(boolean indefinite) {
        this.indefinite = indefinite;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getFavouritesAmmount() {
        return favouritesAmmount;
    }

    public void setFavouritesAmmount(int favouritesAmmount) {
        this.favouritesAmmount = favouritesAmmount;
    }


    public boolean isShowsUser() {
        return showsUser;
    }

    public void setShowsUser(boolean showsUser) {
        this.showsUser = showsUser;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public boolean isInsideMyGroup() {
        return insideMyGroup;
    }

    public void setInsideMyGroup(boolean insideMyGroup) {
        this.insideMyGroup = insideMyGroup;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCommentsAmmount() {
        return commentsAmmount;
    }

    public void setCommentsAmmount(int commentsAmmount) {
        this.commentsAmmount = commentsAmmount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public  List<CommentModel> getComments() {
        return comments;
    }

    public void setComments( List<CommentModel> comments) {
        this.comments = comments;
    }

}
