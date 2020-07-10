package com.example.nckhproject.Activity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.nckhproject.Adapter.PlaceAutoSuggetAdapter;
import com.example.nckhproject.R;
import com.example.nckhproject.Remote.Common;
import com.example.nckhproject.Remote.IGoogleApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MapsActivity extends FragmentActivity implements RoutingListener{

    //google map object
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    protected LatLng start = null;
    protected LatLng end = new LatLng(21.0181642,105.8081334);
    //to get location permissions.
    private final static int LOCATION_REQUEST_CODE = 23;
    //polyline object
    private List<Polyline> polylines = null;
    private PolylineOptions bluePolylineOptions;
    private Marker marker;
    private Handler hander;
    private int index,next;
    FusedLocationProviderClient client;
    private Button btnStart;
    private AutoCompleteTextView autoPlace;
    private List<LatLng> polylineList ;
    IGoogleApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //init google map fragment to show map.
        mapFragment = SupportMapFragment.newInstance();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        autoPlace = findViewById(R.id.place_autocomplete);
        autoPlace.setAdapter(new PlaceAutoSuggetAdapter(this,android.R.layout.simple_list_item_1));

        autoPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMap.clear();
                start = getLatLngFromAddress(autoPlace.getText().toString());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start,17));
                MarkerOptions optionsStart = new MarkerOptions().position(start);
                mMap.addMarker(optionsStart);
                directionApi();
            }
        });
        btnStart = findViewById(R.id.btnStart);
        //initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            getCurrentLocation();
            getCurrentLocationDirections();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST_CODE);
        }
        mService = Common.getGoogleApi();
    }

    private void getCurrentLocationDirections() {
        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @SuppressLint("MissingPermission") // ignore alert
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
                            UiSettings uiSettings = mMap.getUiSettings();
                            uiSettings.setZoomControlsEnabled(true);
                            uiSettings.setIndoorLevelPickerEnabled(true);
                            mMap.setMyLocationEnabled(true);
                            // Initialize
                            start = new LatLng(location.getLatitude(),location.getLongitude());
                            //Creat marker options
                            MarkerOptions optionsEnd = new MarkerOptions().position(end);

                            //Zoom map
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start,17));

                            //Add marker on map
//                                mMap.addMarker(optionsStart);
                            mMap.addMarker(optionsEnd);
                            directionApi();
                        }
                    });
                }
            }
        });
    }

    public void directionApi(){
        String requestUrl = "https://maps.googleapis.com/maps/api/directions/json?mode=driving&ransit_routing_preference=less_driving&origin="
                + start.latitude + "," + start.longitude +
                "&destination="
                + end.latitude + "," + end.longitude +
                "&key=AIzaSyB0X8nWT0s9EHzPCaWZWdfDsFWAzFFqpSA";
        Log.d("AAA",requestUrl);
        mService.getDataFromGoogleApi(requestUrl).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    Log.d("AAA",response.body()+"");
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("routes");
                    jsonObject  = (JsonObject) jsonArray.get(0);
                    JsonObject overview_polyline = jsonObject.getAsJsonObject("overview_polyline");
                    JsonElement points = overview_polyline.get("points");
                    polylineList = decodePoly(points.getAsString());

//                    Adjusting bounds
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for(LatLng latLng:polylineList){
                        builder.include(latLng);
                    }
                    LatLngBounds bounds = builder.build();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,2);
                    mMap.animateCamera(cameraUpdate);

                    Log.d("AAA","polylineList: "+ polylineList.size());
                    bluePolylineOptions = new PolylineOptions();
                    for(int i=0;i<polylineList.size();i++){
                        bluePolylineOptions.add(polylineList.get(i));
                    }
                    bluePolylineOptions.add(end);
                    bluePolylineOptions.color(Color.BLUE);
                    bluePolylineOptions.width(5.0f);
                    bluePolylineOptions.startCap(new SquareCap());
                    bluePolylineOptions.endCap(new SquareCap());
                    bluePolylineOptions.geodesic(true);

                    mMap.addPolyline(bluePolylineOptions);

                    mMap.addMarker(new MarkerOptions().position(polylineList.get(polylineList.size()-1)));

                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Animator
                            marker = mMap.addMarker(new MarkerOptions().position(start));
                            marker.setFlat(true);
                            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.current_user));

                            //Car moving
                            hander = new Handler();
                            index = -1;
                            next = 1;
                            hander.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if( index < polylineList.size() - 1 ){
                                        index += 1 ;
                                        next = index + 1;
                                    }
                                    if(index < polylineList.size() - 1){
                                        start = polylineList.get(index);
                                        end = polylineList.get(next);
                                    }

                                    final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
                                    valueAnimator.setDuration(3000);
                                    valueAnimator.setInterpolator(new LinearInterpolator());
                                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        @Override
                                        public void onAnimationUpdate(ValueAnimator animation) {
                                            float v =  animation.getAnimatedFraction();
                                            double lng = v * end.longitude + ( 1 - v ) * start.longitude; // Tinh Kinh Do
                                            double lat = v * end.latitude + ( 1 - v ) * start.latitude; // Tinh Vi Do
                                            LatLng newPos = new LatLng(lat,lng);
                                            marker.setPosition(newPos);
                                            marker.setAnchor(0.5f,0.5f); // Diem neo
                                            marker.setRotation(getBrearing(start,newPos)); // Phép Xoay
                                            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                                                    .target(newPos)
                                                    .zoom(15.5f)
                                                    .build()));
                                        }
                                    });
                                    valueAnimator.start();
                                    hander.postDelayed(this,3000);
                                }
                            },3000);
                        }
                    });
                } catch (JsonIOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("AAA",t.getMessage());
            }
        });
    }

    //Tinh Goc
    private float getBrearing(LatLng start, LatLng newPos) {
        double lat = Math.abs(start.latitude - newPos.latitude);
        double lng = Math.abs(start.longitude - newPos.longitude);
        if(start.latitude < newPos.latitude && start.longitude < newPos.longitude)
            return (float) Math.toDegrees(Math.atan(lng/lat));
        else if(start.latitude >= newPos.latitude && start.longitude < newPos.longitude)
            return (float) ( 90 - Math.toDegrees((Math.atan(lng/lat))) + 90 );
        else if(start.latitude >= newPos.latitude && start.longitude >= newPos.longitude)
            return (float) ( Math.toDegrees((Math.atan(lng/lat))) + 180 );
        else if(start.latitude < newPos.latitude && start.longitude >= newPos.longitude)
            return (float) ( 90 - Math.toDegrees((Math.atan(lng/lat))) + 270 );
        return -1;
    }
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
    public LatLng getLatLngFromAddress(String address){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList != null){
                Address singleAddress = addressList.get(0);
                LatLng latLng =  new LatLng(singleAddress.getLatitude(),singleAddress.getLongitude());
                return latLng;
            }
            else return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void getCurrentLocation() {
            @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(final Location location) {
                    if(location != null){
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @SuppressLint("MissingPermission") // ignore alert
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                mMap = googleMap;
                                UiSettings uiSettings = mMap.getUiSettings();
                                uiSettings.setZoomControlsEnabled(true);
                                uiSettings.setIndoorLevelPickerEnabled(true);
                                mMap.setMyLocationEnabled(true);
                                // Initialize
                                start = new LatLng(location.getLatitude(),location.getLongitude());
                                //Creat marker options
                                MarkerOptions optionsEnd = new MarkerOptions().position(end);

                                //Zoom map
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start,17));

                                //Add marker on map
                                mMap.addMarker(optionsEnd);
                                Findroutes(start,end);
                            }
                        });
                    }
                }
            });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }

    // function to find Routes.
    public void Findroutes(LatLng Start, LatLng End)
    {
        if(Start==null || End==null) {
            Toast.makeText(this,"Unable to get location",Toast.LENGTH_LONG).show();
        }
        else
        {

            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(Start, End)
                    .key("AIzaSyB0X8nWT0s9EHzPCaWZWdfDsFWAzFFqpSA")  //also define your api key here.
                    .build();
            routing.execute();
        }
    }
    //Routing call back functions.
    @Override
    public void onRoutingFailure(RouteException e) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar= Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_LONG);
        snackbar.show();
        Log.d("AAA",e.toString());
    }

    @Override
    public void onRoutingStart() {
//        Toast.makeText(this,"Finding Route...",Toast.LENGTH_LONG).show();
    }

    //If Route finding success..
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        if(polylines!=null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();
        LatLng polylineStartLatLng=null;
        LatLng polylineEndLatLng=null;


        polylines = new ArrayList<>();
        //add route(s) to the map using polyline
        for (int i = 0; i <route.size(); i++) {

            if(i==shortestRouteIndex)
            {
                polyOptions.color(getResources().getColor(R.color.colorPrimary));
                polyOptions.width(7);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng=polyline.getPoints().get(0);
                int k=polyline.getPoints().size();
                polylineEndLatLng=polyline.getPoints().get(k-1);
                polylines.add(polyline);

            }
            else {

            }

        }

        //Add Marker on route starting position
        MarkerOptions startMarker = new MarkerOptions();
        startMarker.position(polylineStartLatLng);
        startMarker.title("My Location");
        mMap.addMarker(startMarker);

        //Add Marker on route ending position
        MarkerOptions endMarker = new MarkerOptions();
        endMarker.position(polylineEndLatLng);
        endMarker.title("Destination");
        mMap.addMarker(endMarker);
    }

    @Override
    public void onRoutingCancelled() {
        Findroutes(start,end);
    }
}