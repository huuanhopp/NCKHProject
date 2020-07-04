package com.example.nckhproject.Class;

public class Notify_Class {
    private String Time;
    private String Content;

    public Notify_Class(String time, String content) {
        Time = time;
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Notify_Class() {
    }
}
