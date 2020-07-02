package com.example.nckhproject.Class;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User_Class {
    private String User_Name;
    private String User_Email;
    private String User_PhoneNum;
    private String User_Address;
    private String User_Date_of_Registration;
    private String User_Expiration_Date;
    private Boolean Active;

    public User_Class() {
    }

    public User_Class(String user_Name, String user_Email, String user_PhoneNum, String user_Address, String user_Date_of_Registration, String user_Expiration_Date, Boolean active) {
        User_Name = user_Name;
        User_Email = user_Email;
        User_PhoneNum = user_PhoneNum;
        User_Address = user_Address;
        User_Date_of_Registration = user_Date_of_Registration;
        User_Expiration_Date = user_Expiration_Date;
        Active = active;
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

    public String getUser_PhoneNum() {
        return User_PhoneNum;
    }

    public void setUser_PhoneNum(String user_PhoneNum) {
        User_PhoneNum = user_PhoneNum;
    }

    public String getUser_Address() {
        return User_Address;
    }

    public void setUser_Address(String user_Address) {
        User_Address = user_Address;
    }

    public String getUser_Date_of_Registration() {
        return User_Date_of_Registration;
    }

    public void setUser_Date_of_Registration(String user_Date_of_Registration) {
        User_Date_of_Registration = user_Date_of_Registration;
    }

    public String getUser_Expiration_Date() {
        return User_Expiration_Date;
    }

    public void setUser_Expiration_Date(String user_Expiration_Date) {
        User_Expiration_Date = user_Expiration_Date;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
