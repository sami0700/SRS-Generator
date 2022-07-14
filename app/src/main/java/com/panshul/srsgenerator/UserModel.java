package com.panshul.srsgenerator;

public class UserModel {
    String uid;
    String phone;
    String name;
    String email;

    public UserModel(){

    }

    public UserModel(String uid ,String phone, String name, String email) {
        this.uid = uid;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
