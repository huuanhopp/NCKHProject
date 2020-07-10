package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nckhproject.Class.CustomAdapter_Room;
import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Class.Room_Class;
import com.example.nckhproject.Class.User_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class A3Activity extends AppCompatActivity {
    CustomAdapter_Room customAdapter;
    ListView listView;
    ArrayList<Room_Class> list_room;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String[] Name = {"305A3", "302A3", "306A3", "307A3", "302A3"};
    long[] Price = {1200000, 1300000, 1000000, 1400000, 900000};
    int[] Date = {5, 5, 5, 5, 5};
    int[] personMax = {8, 8, 8, 8, 8};
    MyShared_Class myShared_class;

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.itemRegister:
                Dialog(position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void Anhxa() {
        listView = (ListView) findViewById(R.id.lvRoom);
        registerForContextMenu(listView);
    }


    private void init() {
        myShared_class = new MyShared_Class(A3Activity.this);
        list_room = new ArrayList<>();
        customAdapter = new CustomAdapter_Room(A3Activity.this, list_room, R.layout.customlistbuilding);
        listView.setAdapter(customAdapter);
        Update();

    }

    private void Update() {
        list_room.clear();
        databaseReference.child("Room").orderByChild("name").addChildEventListener(new ChildEventListener() {
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

    private void Dialog(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(A3Activity.this);
        dialog.setTitle("Message");
        dialog.setMessage("Bạn có muốn đăng ký ở phòng này không?");
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (list_room.get(position).getPerson_Now().size() == list_room.get(position).getPerson_Max()) {
                    Toast.makeText(A3Activity.this, "Phòng này đã đầy, vui lòng chọn phòng khác", Toast.LENGTH_SHORT).show();
                } else {
                    myShared_class.putBoolen("Active", true);
                    Toast.makeText(A3Activity.this, "Chọn phòng thành công!", Toast.LENGTH_SHORT).show();
                    databaseReference.child("User").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            User_Class user_class = dataSnapshot.getValue(User_Class.class);
                            if (user_class.getUser_Email().equals(myShared_class.getString("Email")) == true) {
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.DAY_OF_MONTH, 3);
                                user_class.setActive(true);
                                user_class.setUser_Date_of_Registration(sdf1.format(cal.getTime()).toString());
                                databaseReference.child("User").child(dataSnapshot.getKey()).setValue(user_class);
                                Intent intent = new Intent(A3Activity.this, DetailRegisterActivity.class);
                                intent.putExtra("NameofRoom", list_room.get(position).getName());
                                intent.putExtra("PriceofRoom", list_room.get(position).getPrice());
                                intent.putExtra("DateofRoom", String.valueOf(list_room.get(position).getDate()));
                                startActivity(intent);
                            }
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
                    databaseReference.child("Room").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            Room_Class room_class = dataSnapshot.getValue(Room_Class.class);
                            if (room_class.getName().equals(list_room.get(position).getName()) == true) {
                                room_class.AddUser(myShared_class.getString("Email"));
                                databaseReference.child("Room").child(dataSnapshot.getKey()).setValue(room_class);
                                list_room.get(position).AddUser(myShared_class.getString("Email"));
                                customAdapter.notifyDataSetChanged();
                                finish();
                            }
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
        });

        dialog.show();
    }
}