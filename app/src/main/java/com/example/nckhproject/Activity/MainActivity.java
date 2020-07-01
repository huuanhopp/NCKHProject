package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Fragment.History_Fragment;
import com.example.nckhproject.Fragment.Home_Fragment;
import com.example.nckhproject.Fragment.Message_Fragment;
import com.example.nckhproject.Fragment.Profile_Fragment;
import com.example.nckhproject.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View headerView;
    TextView navUserName;
    ImageView navImgUser;
    MyShared_Class myShared_class;
    Button btnHome;
    Button btnMessage;
    Button btnHistory;
    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myShared_class = new MyShared_Class(this);
        FramentManagerconstruction(new Home_Fragment());
        Anhxa();
        Toolbar_Nagavition();
        Listener();

    }

    private void Listener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FramentManagerconstruction(new Home_Fragment());
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FramentManagerconstruction(new Profile_Fragment());
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FramentManagerconstruction(new Message_Fragment());
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FramentManagerconstruction(new History_Fragment());
            }
        });
    }

    private void FramentManagerconstruction(Fragment a) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frmContent, a).addToBackStack(null).commit();


    }

    private void Toolbar_Nagavition() {
        setSupportActionBar(toolbar);

        //menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Anhxa() {
        drawerLayout = findViewById(R.id.layout_main);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar);

        headerView = navigationView.getHeaderView(0);
        navUserName = (TextView) headerView.findViewById(R.id.txt_nameUser);
        navImgUser = (ImageView) headerView.findViewById(R.id.img_user);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        btnProfile = (Button) findViewById(R.id.btnProfile);
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
            case R.id.nav_Login:

                break;
            case R.id.nav_profile:

                break;
            case R.id.nav_Logout:
                myShared_class.clear();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }




}
