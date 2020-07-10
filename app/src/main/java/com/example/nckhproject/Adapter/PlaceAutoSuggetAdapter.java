package com.example.nckhproject.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;

import com.example.nckhproject.Remote.Common;
import com.example.nckhproject.Remote.IGoogleApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceAutoSuggetAdapter extends ArrayAdapter implements Filterable {
    ArrayList<String> results = new ArrayList<>();
    int resource;
    Context context;
    IGoogleApi iGoogleApi = Common.getGoogleApi();

    public PlaceAutoSuggetAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public String getItem(int position) {
        return results.get(position);
    }

    @Override
    public Filter getFilter(){
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                if(constraint != null){
                 iGoogleApi.getDataFromGoogleApi("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
                         +constraint+
                         "&location=21.0181642,105.8081334&radius=20000"+
                         "&key=AIzaSyB0X8nWT0s9EHzPCaWZWdfDsFWAzFFqpSA").enqueue(new Callback<JsonObject>() {
                     @Override
                     public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                         JsonArray predictions = response.body().getAsJsonArray("predictions");
                         results.clear();
                         for (int i = 0 ; i < predictions.size() ; i++){
                             JsonObject jsonObject = (JsonObject) predictions.get(i);
                             results.add(jsonObject.get("description").getAsString());
                         }
                     }

                     @Override
                     public void onFailure(Call<JsonObject> call, Throwable t) {
                        notifyDataSetChanged();
                     }
                 });
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0){
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
