package com.example.nckhproject.Remote;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface IGoogleApi {
//    @GET("json?origin={startLatitude},{startLongitude}&destination={endLatitude},{endLongitude&key}&key=AIzaSyB0X8nWT0s9EHzPCaWZWdfDsFWAzFFqpSA")
//    Call<JSONObject> getDataFromGoogleApi(@Part("startLatitude") Double startLatitude, @Part("startLongitude") Double startLongitude, @Part("endLatitude") Double endLatitude, @Part("endLongitude") Double endLongitude);
    @GET
    Call<JsonObject> getDataFromGoogleApi(@Url String url);
    @GET
    Call<JsonObject> getPlaceAutoSugget(@Url String url);
}
