package com.example.nckhproject;

public class User {
    private int ID_User;
    private String Password_User;
    private String Name_User;
    private String Email_User;
    private String PNumber_User;
    private String Address_User;
    private String Class_User;

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getPassword_User() {
        return Password_User;
    }

    public void setPassword_User(String password_User) {
        Password_User = password_User;
    }

    public String getName_User() {
        return Name_User;
    }

    public void setName_User(String name_User) {
        Name_User = name_User;
    }

    public String getEmail_User() {
        return Email_User;
    }

    public void setEmail_User(String email_User) {
        Email_User = email_User;
    }

    public String getPNumber_User() {
        return PNumber_User;
    }

    public void setPNumber_User(String PNumber_User) {
        this.PNumber_User = PNumber_User;
    }

    public String getAddress_User() {
        return Address_User;
    }

    public void setAddress_User(String address_User) {
        Address_User = address_User;
    }

    public String getClass_User() {
        return Class_User;
    }

    public void setClass_User(String class_User) {
        Class_User = class_User;
    }

    public User(int ID_User, String password_User, String name_User, String email_User, String PNumber_User, String address_User, String class_User) {
        this.ID_User = ID_User;
        Password_User = password_User;
        Name_User = name_User;
        Email_User = email_User;
        this.PNumber_User = PNumber_User;
        Address_User = address_User;
        Class_User = class_User;
    }
}
