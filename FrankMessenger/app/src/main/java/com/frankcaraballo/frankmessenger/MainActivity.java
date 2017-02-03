package com.frankcaraballo.frankmessenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Handler;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    EditText txtMsgEditText,pNumEditText,messagesEditText;
    Button sendButton;

    Handler mHandler = new Handler();

    static String messages = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtMsgEditText = (EditText) findViewById(R.id.txtMsgEditText);
        pNumEditText =  (EditText) findViewById(R.id.pNumberEditText);
        messagesEditText =  (EditText) findViewById(R.id.messagesEditText);

        sendButton = (Button) findViewById(R.id.sendButton);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){

                    try {
                        Thread.sleep(5000);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                messagesEditText.setText(messages);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

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

    public void sendMessage(View view) {

        String phoneNumer = pNumEditText.getText().toString();
        String message = txtMsgEditText.getText().toString();

        if(message != null){
        try{

            SmsManager smsManager = SmsManager.getDefault();



            smsManager.sendTextMessage(phoneNumer,null,message,null,null);


            Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();

        }catch (IllegalArgumentException ex){

            Toast.makeText(this,"Enter a Phone Number and Message",Toast.LENGTH_SHORT).show();
            ex.printStackTrace();

        }

        }else {
            Toast.makeText(this,"Message Not Sent",Toast.LENGTH_SHORT).show();
        }


        messages = messages + "You : " + message +"\n";

    }


    public static class SmsReceiver extends BroadcastReceiver{

        final SmsManager smsManager = SmsManager.getDefault();

        public SmsReceiver(){}

        @Override
        public void onReceive(Context context, Intent intent) {

            final Bundle bundle = intent.getExtras();

            try {

                if(bundle != null){

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i =0;i<pdusObj.length;i++){

                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                        String phoneNumber = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getDisplayMessageBody();

                        messages = messages + phoneNumber + " : " + message +"\n";

                    }

                }

            }catch (Exception ex){

                Log.e("SmsReceiver", "Exception SMS Receiver");

            }

        }
    }

    public class MMSReceiver extends BroadcastReceiver{

        MMSReceiver(){}

        @Override
        public void onReceive(Context context, Intent intent) {

            throw new UnsupportedOperationException("Not Implemented Yet");
        }
    }

    public class HeadlessSmsSendService extends BroadcastReceiver{

        HeadlessSmsSendService(){}

        @Override
        public void onReceive(Context context, Intent intent) {

            throw new UnsupportedOperationException("Not Implemented Yet");
        }
    }

    @Override
    protected void onDestroy() {

        mHandler.removeCallbacksAndMessages(null);

        super.onDestroy();
    }
}
