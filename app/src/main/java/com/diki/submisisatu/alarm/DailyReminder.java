package com.diki.submisisatu.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import com.diki.submisisatu.R;

import timber.log.Timber;

public class DailyReminder extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "Daily Reminder";
    public static CharSequence CHANNEL_NAME = "Pokemon Channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.i("Masuk ke dalam daily reminder");
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Ini adalah Daily Reminder")
                .setContentText("Isi dari daily reminder, silakan bahagia :)")
                .setSubText("Jangan lupa bahagia")
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
