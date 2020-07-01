package com.example.nckhproject.Class;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShared_Class {
    private SharedPreferences preferences;

    public MyShared_Class(Context context) {
        preferences = context.getSharedPreferences(
                "MyShared",
                Context.MODE_PRIVATE);
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void clear(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    public void remove(String key){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }


    public String get(String key) {
        return preferences.getString(key, "");
    }

}
