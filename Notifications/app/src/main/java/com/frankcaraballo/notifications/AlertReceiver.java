package com.frankcaraballo.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by frank on 02/03/15.
 */
public class AlertReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, "Time is Up", "5 seconds Has Passed", "Alert");
    }


    public void createNotification(Context context, String msg, String msgText, String  msgAlert){

        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context,MainActivity.class),0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).
                setSmallIcon(R.drawable.ninja).setContentTitle(msg).setTicker(msgAlert).setContentText(msgText);

        mBuilder.setContentIntent(notificIntent);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1,mBuilder.build());

    }
}
