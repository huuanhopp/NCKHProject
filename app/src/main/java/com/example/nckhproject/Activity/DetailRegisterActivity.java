package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Class.User_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class DetailRegisterActivity extends AppCompatActivity {
    TextView tvName, tvPrice, tvDate;
    Button btnCancel, btnBack;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    MyShared_Class myShared_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        Mapping();
        Init();
        Listener();
    }
    public String getStringPrice(long Price)
    {
        StringBuilder args = new StringBuilder(String.valueOf(Price)).reverse();
        String res = "";
        for(int i = 0; i < args.length(); i ++)
        {
            res += args.charAt(i);
            if((i+1)%3 == 0 && i!= args.length()-1)
                res += ",";
        }
        return new StringBuffer(res).reverse().toString();
    }

    private void Init() {
        myShared_class = new MyShared_Class(DetailRegisterActivity.this);
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("NameofRoom"));
        tvPrice.setText(getStringPrice(intent.getLongExtra("PriceofRoom", 0)) + " VNĐ /" + intent.getStringExtra("DateofRoom") + " tháng");
        databaseReference.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String Email = myShared_class.getString("Email");
                User_Class user_class = dataSnapshot.getValue(User_Class.class);
                if(user_class.getUser_Email().equals(Email) == true)
                {
                    tvDate.setText(user_class.getUser_Date_of_Registration());
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
    }

    private void Mapping() {
        tvName = findViewById(R.id.tv_NameRoom_Detail);
        tvPrice = findViewById(R.id.tv_PriceRoomDetail);
        tvDate = findViewById(R.id.tv_Date_EX);
        btnBack = findViewById(R.id.btn_BackDetail);
        btnCancel = findViewById(R.id.btn_CancelRoomDetail);
    }
}