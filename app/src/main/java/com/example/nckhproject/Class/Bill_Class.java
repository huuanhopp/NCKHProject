package com.example.nckhproject.Class;

public class Bill_Class {
    private String Date_of_Registration;
    private String Expiration_Date;
    private long Price;
    private String User_Email;

    public Bill_Class(String date_of_Registration, String expiration_Date, long price, String user_Email) {
        Date_of_Registration = date_of_Registration;
        Expiration_Date = expiration_Date;
        Price = price;
        User_Email = user_Email;
    }

    public Bill_Class() {
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getDate_of_Registration() {
        return Date_of_Registration;
    }

    public void setDate_of_Registration(String date_of_Registration) {
        Date_of_Registration = date_of_Registration;
    }

    public String getExpiration_Date() {
        return Expiration_Date;
    }

    public void setExpiration_Date(String expiration_Date) {
        Expiration_Date = expiration_Date;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }
}
