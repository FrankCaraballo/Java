package com.frankcaraballo.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity {

    Button showNotificationBtn, stopNotificationBtn, alertBtn;

    NotificationManager notificationManager;

    boolean isNotificActive = false;

    int notifID = 33;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotificationBtn = (Button) findViewById(R.id.showNotificationBtn);

        stopNotificationBtn = (Button) findViewById(R.id.stopNotificationBtn);

        alertBtn = (Button) findViewById(R.id.alertInFiveBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNotification(View view) {

        NotificationCompat.Builder notifcBuilder = new NotificationCompat.Builder(this).
                setContentTitle("MESSAGE").
                setContentText("New Message").
                setTicker("Alert New Message").
                setSmallIcon(R.drawable.ninja);


        Intent moreInfo = new Intent(this,MoreInfoNotification.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);

        taskStackBuilder.addNextIntent(moreInfo);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notifcBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID,notifcBuilder.build());

        isNotificActive = true;

    }

    public void stopNotification(View view) {

        if(isNotificActive){

            notificationManager.cancel(notifID);

        }

    }

    public void setAlarm(View view) {

        isNotificActive = true;

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager)  getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,alertTime,PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));


    }
}
