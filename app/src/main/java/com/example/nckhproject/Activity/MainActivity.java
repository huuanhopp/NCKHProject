package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Fragment.History_Fragment;
import com.example.nckhproject.Fragment.Home_Fragment;
import com.example.nckhproject.Fragment.Notify_Fragment;
import com.example.nckhproject.Fragment.Profile_Fragment;
import com.example.nckhproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View headerView;
    TextView navUserName;
    static String userEmail;
    TextView navUserEmail;
    ImageView navImgUser;
    MyShared_Class myShared_class;
    BottomNavigationView navbuttom;
    int typeButtonNav = 1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotificationsNCKH","MyNotificationsNCKH", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            Log.d("LOG","Nam Doe Zoia");
        }

        FirebaseMessaging.getInstance().subscribeToTopic("nckh")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "Failse";
                        }
                        Log.d("AAA", msg);
                    }
                });
        myShared_class = new MyShared_Class(this);
        FramentManagerconstruction(new Home_Fragment());
        Anhxa();
        Nagavition();
        Listener();
        Init();
    }

    private void Init() {
        navUserEmail.setText(myShared_class.getString("Email"));
        navUserName.setText(myShared_class.getString("Name"));
        userEmail = myShared_class.getString("Email");
    }

    private void Listener() {
        BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int type = 1;
                switch (item.getItemId())
                {
                    case R.id.nav_buttom_home:
                        selectedFragment = new Home_Fragment();
                        type = 1;
                        break;
                    case R.id.nav_buttom_history:
                        selectedFragment = new History_Fragment();
                        type = 2;
                        break;
                    case R.id.nav_buttom_message:
                        selectedFragment = new Notify_Fragment();
                        type = 3;
                        break;
                    case R.id.nav_buttom_profile:
                        selectedFragment = new Profile_Fragment();
                        type =4;
                        break;
                }
                if(type != typeButtonNav)
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frmContent, selectedFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                    typeButtonNav = type;
                }
                return true;
            }
        };
        navbuttom.setOnNavigationItemSelectedListener(navListener);
    }

    private void FramentManagerconstruction(Fragment a) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frmContent, a).addToBackStack(null).commit();


    }
//
    private void Nagavition() {

        //menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Anhxa() {
        navbuttom = findViewById(R.id.nav_buttom);
        drawerLayout = findViewById(R.id.layout_main);
        navigationView = findViewById(R.id.nav_view);

        headerView = navigationView.getHeaderView(0);
        navUserName = (TextView) headerView.findViewById(R.id.user_name);
        navUserEmail = (TextView) headerView.findViewById(R.id.user_email);

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_map:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_Logout:
                myShared_class.clear();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                intent = new Intent(MainActivity.this, MessageAcitivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
