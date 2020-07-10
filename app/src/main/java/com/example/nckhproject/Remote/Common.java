package com.example.nckhproject.Remote;

public class Common {
    public static final String baseUrl = "https://maps.googleapis.com";
    public static IGoogleApi getGoogleApi(){
        return RetrofitClient.getClient(baseUrl).create(IGoogleApi.class);
    }
}
