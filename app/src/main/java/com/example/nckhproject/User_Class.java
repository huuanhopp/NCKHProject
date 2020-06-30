package com.example.nckhproject;

public class User_Class {
    private String User_Name;
    private String User_Email;

    public User_Class() {
    }

    public User_Class(String user_Name, String user_Email) {
        User_Name = user_Name;
        User_Email = user_Email;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }
}
