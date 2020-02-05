package com.hoyt.pigeondb.pigeons;

public class Pigeons {
    private  String pigeonID, group, gender, mothersID, fathersID, picURL;

    public Pigeons() {

    }
    public Pigeons(String pigeonID, String group, String gender, String mothersID, String fathersID, String picURL) {
        this.pigeonID = pigeonID;
        this.group = group;
        this.gender = gender;
        this.mothersID = mothersID;
        this.fathersID = fathersID;
        this.picURL = picURL;
    }


    public String getPicURL() {
        return picURL;
    }

    public Pigeons setPicURL(String picURL) {
        this.picURL = picURL;
        return this;
    }

    public String getPigeonID() {
        return pigeonID;
    }

    public Pigeons setPigeonID(String pigeonID) {
        this.pigeonID = pigeonID;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public Pigeons setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Pigeons setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getMothersID() {
        return mothersID;
    }

    public Pigeons setMothersID(String mothersID) {
        this.mothersID = mothersID;
        return this;
    }

    public String getFathersID() {
        return fathersID;
    }

    public Pigeons setFathersID(String fathersID) {
        this.fathersID = fathersID;
        return this;
    }
}
