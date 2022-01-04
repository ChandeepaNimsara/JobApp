package com.example.jobapp;

public class User {

    public String fullName, regid, role, email;

    User(){

    }

    public User(String fullName, String regid, String role, String email){
        this.fullName = fullName;
        this.regid = regid;
        this.role = role;
        this.email = email;
    }

}
