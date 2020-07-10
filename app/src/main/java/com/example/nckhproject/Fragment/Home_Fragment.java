package com.example.nckhproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nckhproject.Activity.A3Activity;
import com.example.nckhproject.Activity.MainActivity;
import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Class.Room_Class;
import com.example.nckhproject.Class.User_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home_Fragment extends Fragment {
    Button btnA1, btnA3,btnA4, btnA5, btnA6;
    View view;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    MyShared_Class myShared_class;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        Anhxa();
        getData();
        Listener();
        return view;

    }

    private void getData() {
        myShared_class = new MyShared_Class(view.getContext());
        databaseReference.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User_Class user_class = dataSnapshot.getValue(User_Class.class);
                if(user_class.getUser_Email().equals(myShared_class.getString("Email")) == true)
                {
//                    myShared_class.putBoolen("Active", user_class.getActive());
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

    private void Listener() {
        btnA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Tòa nhà này không dành cho sinh viên!", Toast.LENGTH_SHORT).show();
            }
        });
        btnA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myShared_class.getBoolean("Active") == false)
                {
                    Intent intent = new Intent(view.getContext(), A3Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(view.getContext(),"Bạn đã đăng ký rồi, vui lòng không đăng ký lại", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void Anhxa() {
        btnA6 = view.findViewById(R.id.btnA6);
        btnA1 = view.findViewById(R.id.btnA1);
        btnA3 = view.findViewById(R.id.btnA3);
        btnA4 = view.findViewById(R.id.btnA4);
        btnA5 = view.findViewById(R.id.btnA5);
    }



}
