package com.example.nckhproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nckhproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ContentlNotifyActivity extends AppCompatActivity {
    TextView txtContent;
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentl_notify);
        Anhxa();
        GetData();
    }

    private void GetData() {
        Intent intent = getIntent();
        String Content = intent.getStringExtra("content");
        String Title = intent.getStringExtra("title");
        txtContent.setText(Content);
        txtTitle.setText(Title);
    }

    private void Anhxa() {
        txtContent = (TextView) findViewById(R.id.txtContent);
        txtTitle = (TextView) findViewById(R.id.txtTitle_Content);
    }
}