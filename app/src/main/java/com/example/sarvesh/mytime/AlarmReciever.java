package com.example.sarvesh.mytime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,
                "AlarmReceiver.onReceive()",
                Toast.LENGTH_LONG).show();
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds
        v.vibrate(400);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        Intent resultIntent = new Intent(context, ScrollingActivity.class);

// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        int mNotificationId = 001;
        mBuilder.setContentIntent(resultPendingIntent);
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}
