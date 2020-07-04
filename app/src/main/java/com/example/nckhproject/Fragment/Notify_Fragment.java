package com.example.nckhproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nckhproject.Activity.ContentlNotifyActivity;
import com.example.nckhproject.Class.CustomAdapter_Notify;
import com.example.nckhproject.Class.Notify_Class;
import com.example.nckhproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class Notify_Fragment extends Fragment {
    View view;
    ListView lvNotify;
    ArrayList<Notify_Class> list_Notify;
    CustomAdapter_Notify customAdapter_notify;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notify_fragment, container, false);
        Anhxa();
        Init();
        Listener();
        return view;
    }

    private void Listener() {
        lvNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ContentlNotifyActivity.class);
                intent.putExtra("content", list_Notify.get(position).getContent());
                intent.putExtra("title", list_Notify.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    private void Init() {
        list_Notify = new ArrayList<>();
        customAdapter_notify = new CustomAdapter_Notify(view.getContext(), list_Notify, R.layout.customnotify);
        lvNotify.setAdapter(customAdapter_notify);
        databaseReference.child("Notify").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                list_Notify.add(dataSnapshot.getValue(Notify_Class.class));
                Collections.reverse(list_Notify);
                customAdapter_notify.notifyDataSetChanged();
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

    private void Anhxa() {
        lvNotify = (ListView) view.findViewById(R.id.lvNotify);
    }
}
