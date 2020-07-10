package com.example.nckhproject.Class;

import java.util.ArrayList;
import java.util.Collection;

public class Room_Class {
    private String Name;
    private long Price;
    private long Date;
    private ArrayList<String> Person_Now = new ArrayList<>();
    private int Person_Max;

    public Room_Class() {
    }

    public Room_Class(String name, long price, long date, ArrayList<String> person_Now, int person_Max) {
        Name = name;
        Price = price;
        Date = date;
        Person_Now = person_Now;
        Person_Max = person_Max;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public ArrayList<String> getPerson_Now() {
        return Person_Now;
    }

    public void setPerson_Now(ArrayList<String> person_Now) {
        Person_Now = person_Now;
    }

    public int getPerson_Max() {
        return Person_Max;
    }

    public void setPerson_Max(int person_Max) {
        Person_Max = person_Max;
    }

    public void AddUser(String Email)
    {
        this.Person_Now.add(Email);
    }


}
