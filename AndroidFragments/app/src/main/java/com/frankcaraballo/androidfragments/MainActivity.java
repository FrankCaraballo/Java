package com.frankcaraballo.androidfragments;



import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;



public class MainActivity extends Activity  {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //setContentView(R.layout.activity_main);

       FragmentManager fragmentManager = FragmentActivity.getFragmentManager();

       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

       Configuration configInfo =  getResources().getConfiguration();


        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){

            FragmentLandscape fragmentLandscape = new FragmentLandscape();

            FragmentTransaction.replace(android.R.id.content,fragmentLandscape);



        } else {

            FragmentPortrait fragmentPortrait = new FragmentPortrait();

            FragmentTransaction.replace(android.R.id.content,fragmentPortrait);

        }
        fragmentTransaction.commit();

    }


    /*@Override
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
    }*/
}
