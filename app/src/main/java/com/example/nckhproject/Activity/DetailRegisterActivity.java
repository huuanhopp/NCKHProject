package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.Class.User_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailRegisterActivity extends AppCompatActivity {
    TextView tvName, tvPrice, tvDate;
    Button btnCancel, btnBack, btn_Dialog_Cancel, btn_Dialog_Accept;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    MyShared_Class myShared_class;
    Dialog dlRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);
        Mapping();
        Dialog();
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

    private void Dialog(){
        dlRemove = new Dialog(DetailRegisterActivity.this);
        dlRemove.setContentView(R.layout.custom_dialog);
        dlRemove.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_Dialog_Cancel = dlRemove.findViewById(R.id.btn_Dialog_Cancel);
        btn_Dialog_Accept = dlRemove.findViewById(R.id.btn_Dialog_Accept);
    }

    private void Listener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        User_Class user_class = dataSnapshot.getValue(User_Class.class);
                        if(user_class.getUser_Email().equals(myShared_class.getString("Email")) == true)
                        {
                            dlRemove.show();
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
        });
        btn_Dialog_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlRemove.cancel();
            }
        });
        btn_Dialog_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        User_Class user_class = dataSnapshot.getValue(User_Class.class);
                        if(user_class.getUser_Email().equals(myShared_class.getString("Email")) == true)
                        {
                            user_class.setActive(false);
                            user_class.setUser_Date_of_Registration("");
                            databaseReference.child("User").child(dataSnapshot.getKey()).setValue(user_class);
                            Toast.makeText(DetailRegisterActivity.this, "Hủy chọn phòng thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailRegisterActivity.this, MainActivity.class);
                            startActivity(intent);
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
        });
    }

    private void Mapping() {
        tvName = findViewById(R.id.tv_NameRoom_Detail);
        tvPrice = findViewById(R.id.tv_PriceRoomDetail);
        tvDate = findViewById(R.id.tv_Date_EX);
        btnBack = findViewById(R.id.btn_BackDetail);
        btnCancel = findViewById(R.id.btn_CancelRoomDetail);

    }
}