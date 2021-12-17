package com.quasartec.spot.models;

import java.util.ArrayList;

public class SpotFilter {
   static  int distance;
    boolean withoutEnd, happeningNow, notStarted, seen, notSeen;
    Category category;
    static public float lat, lon;
String group;
  ArrayList<String> keywords;
String user;

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isNotSeen() {
        return notSeen;
    }

    public void setNotSeen(boolean notSeen) {
        this.notSeen = notSeen;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String orderBy;
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public static int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isWithoutEnd() {
        return withoutEnd;
    }

    public void setWithoutEnd(boolean withoutEnd) {
        this.withoutEnd = withoutEnd;
    }

    public boolean isHappeningNow() {
        return happeningNow;
    }

    public void setHappeningNow(boolean happeningNow) {
        this.happeningNow = happeningNow;
    }

    public boolean isNotStarted() {
        return notStarted;
    }

    public void setNotStarted(boolean notStarted) {
        this.notStarted = notStarted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }



    public void setOrderBy(String orderBy){
        this.orderBy=orderBy;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public SpotFilter(int distance, boolean withoutEnd, boolean happeningNow, boolean notStarted, Category category, String orderBy, String group) {
        this.distance = distance;
        this.withoutEnd = withoutEnd;
        this.happeningNow = happeningNow;
        this.notStarted = notStarted;
        this.category = category;
        this.keywords=new ArrayList<>();
        this.orderBy=orderBy;
        this.group=group;
    }

    public void clearKeyywords(){
        keywords.clear();
    }

    public void addKeyword(String keyword){
        keywords.add(keyword);
    }
}
