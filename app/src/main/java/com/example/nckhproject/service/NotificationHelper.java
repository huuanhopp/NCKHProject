package com.example.nckhproject.service;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.nckhproject.R;

public class NotificationHelper {
    public static void displayNotification(Context context, String title, String body){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "MyNotificationsNCKH")
                            .setContentTitle(title)
                            .setSmallIcon(R.drawable.ic_noticafition)
                            .setAutoCancel(true)
                            .setContentText(body);
        NotificationManagerCompat notificationCompat = NotificationManagerCompat.from(context);
        notificationCompat.notify(999, mBuilder.build());
    }
}
