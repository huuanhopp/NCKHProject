package com.example.nckhproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nckhproject.Adapter.MessageAdapter;
import com.example.nckhproject.R;
import com.example.nckhproject.model.Chat;
import com.example.nckhproject.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class MessageAcitivity extends AppCompatActivity {
    ImageView userProfileImage;
    TextView userName;

    FirebaseUser fUser;
    Intent intent;
    ImageButton btnSent;
    EditText txtSent;

    MessageAdapter messageAdapter;
    List<Chat> chatList;

    RecyclerView recyclerView;

    DatabaseReference reference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_message);

//        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userProfileImage = findViewById(R.id.user_profile_pic);
        userName = findViewById(R.id.user_name);
        btnSent = findViewById(R.id.btnSend);
        txtSent = findViewById(R.id.txtSend);
        recyclerView = findViewById(R.id.userRecycaleView);

        intent = getIntent();

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("user_Email");

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txtSent.getText().toString();
                if( !msg.trim().equals("")){
                    sendMessage(MainActivity.userEmail,"ADMIN",msg);
                }else{
                    Toast.makeText(MessageAcitivity.this,"You can't send empty message",Toast.LENGTH_SHORT).show();
                }
                txtSent.setText("");
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);
    }
}
