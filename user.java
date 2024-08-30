package com.example.registerloginapp;

public class user {
    String kitu, phone, profileImageUrl;

    public user(String kitu, String phone, String profileImageUrl) {
        this.kitu = kitu;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
    }

    public user(){

    }

    public String getKitu() {
        return kitu;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
