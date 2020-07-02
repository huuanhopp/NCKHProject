package com.example.nckhproject.Fragment;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nckhproject.Class.MyShared_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_Fragment extends Fragment {
    EditText edtPhoneNum;
    EditText edtAddress;
    Button btnActive;
    Button btnUpdate;
    TextView txtEmail;
    TextView txtName;
    TextView txtDOR;
    TextView txtED;
    MyShared_Class myShared_class;
    View view;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        myShared_class = new MyShared_Class(view.getContext());
        Anhxa();
        Listener();
        return view;
    }

    private void Listener() {
        txtEmail.setText(myShared_class.getString("Email"));
        txtName.setText(myShared_class.getString("Name"));
        txtDOR.setText(myShared_class.getString("Date_of_Registration"));
        txtED.setText(myShared_class.getString("Expiration_Date"));
        edtPhoneNum.setText(myShared_class.getString("PhoneNum"));
        edtAddress.setText(myShared_class.getString("Address"));
        if(myShared_class.getBoolean("Active") == true)
        {
            btnActive.setText("Đã kích hoạt");
        }
        else
        {
            btnActive.setText("Chưa kích hoạt");
        }
        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnActive.getText().equals("Đã kích hoạt") == true)
                {
                    Toast.makeText(view.getContext(), "Bạn đã đăng ký phòng rồi", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(view.getContext(), "Vui lòng chọn phòng và đến nhà A5 để hoàn tất đăng ký!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    private void Anhxa() {
        edtPhoneNum = (EditText) view.findViewById(R.id.edtPhoneNum);
        edtAddress = (EditText) view.findViewById(R.id.edtAddress);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtDOR = (TextView) view.findViewById(R.id.txtDOR);
        txtED = (TextView) view.findViewById(R.id.txtED);
        btnActive = (Button) view.findViewById(R.id.btnActive);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);

    }

}
