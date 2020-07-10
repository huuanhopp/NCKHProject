package com.example.nckhproject.service;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFireBaseNotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null){
            Map<String,String> data = remoteMessage.getData();
//            String title = data.get("title");
//            String content = data.get("content");
            String title = remoteMessage.getNotification().getTitle();
            String content = remoteMessage.getNotification().getBody();
            NotificationHelper.displayNotification(this, title, content);
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
//        sendRegistrationToServer(token);
    }
}
