package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nckhproject.Class.CustomAdapter;
import com.example.nckhproject.Class.Room_Class;
import com.example.nckhproject.Class.User_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class A3Activity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    ArrayList<Room_Class> list_room;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);
        Anhxa();
        init();
        Menu();
    }

    private void Menu() {
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.selectionmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemRegister:
                Toast.makeText(A3Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void Anhxa() {
        listView = (ListView) findViewById(R.id.lvRoom);
        registerForContextMenu(listView);
    }

    private void init() {
        list_room = new ArrayList<>();
        final ArrayList<String> list_user = new ArrayList<>();
        customAdapter = new CustomAdapter(A3Activity.this, list_room, R.layout.customlistbuilding);
        listView.setAdapter(customAdapter);
        databaseReference.child("Room").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                list_room.add(dataSnapshot.getValue(Room_Class.class));
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}