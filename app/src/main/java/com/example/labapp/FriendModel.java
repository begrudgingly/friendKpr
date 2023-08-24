package com.example.labapp;

public class FriendModel {
    private String friendName, friendDob, friendPhone, friendEmail, friendAddress, friendGender;
    private int id;

    public String getFriendName() {
        return friendName;
    }
    public String getFriendDob() {
        return friendDob;
    }
    public String getFriendPhone() {
        return friendPhone;
    }
    public String getFriendEmail() {
        return friendEmail;
    }
    public String getFriendAddress() {
        return friendAddress;
    }
    public String getFriendGender() {
        return friendGender;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    public void setFriendDob(String friendDob) {
        this.friendDob = friendDob;
    }
    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }
    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }
    public void setFriendAddress(String friendAddress) {
        this.friendAddress = friendAddress;
    }
    public void setFriendGender(String friendGender) {
        this.friendGender = friendGender;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public FriendModel(String friendName, String friendDob, String friendPhone, String friendEmail, String friendAddress, String friendGender) {
        this.friendName = friendName;
        this.friendDob = friendDob;
        this.friendPhone = friendPhone;
        this.friendEmail = friendEmail;
        this.friendAddress = friendAddress;
        this.friendGender = friendGender;
    }
}
