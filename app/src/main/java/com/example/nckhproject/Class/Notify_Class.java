package com.example.nckhproject.Class;

public class Notify_Class {
    private String Date;
    private String Content;
    private String Title;

    public Notify_Class(String date, String content, String title) {
        Date = date;
        Content = content;
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Notify_Class() {
    }
}
