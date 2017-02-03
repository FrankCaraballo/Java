package com.frankcaraballo.caraballomaps;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    static final LatLng myPos = new LatLng(34.6939,135.5022);

    private GoogleMap googleMap ;

    EditText addressEditText,finalAddressEditText;

    LatLng addressPos,finalAddressPos;

    Marker addressMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressEditText = (EditText) findViewById(R.id.addressEditText);
        finalAddressEditText = (EditText) findViewById(R.id.finalAddressEditText2);



        try{
            if(googleMap == null){

                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }

            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            googleMap.setMyLocationEnabled(true);

            googleMap.setTrafficEnabled(true);

            googleMap.setBuildingsEnabled(true);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            Marker marker = googleMap.addMarker(new MarkerOptions().position(myPos).title("Here We GO"));


        }catch (Exception ex){

            ex.printStackTrace();
        }
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

    public void getDirections(View view) {

        String startingAddress = addressEditText.getText().toString();
        String finalAddress = finalAddressEditText.getText().toString();

        if((startingAddress.equals("")) || (finalAddress.equals(""))){

            Toast.makeText(this,"Enter a Starting and Final Address",Toast.LENGTH_LONG).show();
        }else {

            new GetDirections().execute(startingAddress,finalAddress);


        }

    }

    class GetDirections extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... params) {

            String startingAddress = params[0];
            startingAddress = startingAddress.replaceAll(" ","%20");

            getLatLng(startingAddress,false);

            String endingAddress = params[1];
            endingAddress = endingAddress.replaceAll(" ", "%20");

            getLatLng(endingAddress,true);


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            String geoUriString = "http://maps.google.com/maps?addr="+ addressPos.latitude + "," + addressPos.longitude
                    + "&daddr=" + finalAddressPos.latitude + ","+finalAddressPos.longitude;

            Intent mapCall = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUriString));

            startActivity(mapCall);

        }
    }

    public void showAddressMarker(View view) {

        String newAddress = addressEditText.getText().toString();

        if (newAddress != null){

            new PlaceAMarker().execute(newAddress);
        }
    }

    class PlaceAMarker extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... params) {

            String starAddress = params[0];

            starAddress = starAddress.replaceAll(" ","%20");

            getLatLng (starAddress,false);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            addressMarker = googleMap.addMarker(new MarkerOptions().position(addressPos).title("Address"));

        }
    }

    protected void getLatLng(String address, boolean setDestination){

        String uri  = "http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false";

        HttpGet httpGet = new HttpGet(uri);

        HttpClient client = new DefaultHttpClient();

        HttpResponse response ;

        StringBuilder stringBuilder = new StringBuilder();

        try{

            response = client.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            InputStream stream = httpEntity.getContent();


            int byteData;


            while ((byteData = stream.read()) != -1){

                stringBuilder.append((char) byteData);

            }


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double lat = 0.0, lng = 0.0;

        JSONObject jsonObject;

        try{

            jsonObject = new JSONObject(stringBuilder.toString());

            lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0).
                    getJSONObject("geometry").getJSONObject("location").getDouble("lng");

            lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0).
                    getJSONObject("geometry").getJSONObject("location").getDouble("lat");


            if(setDestination){

                finalAddressPos = new LatLng(lat,lng);

            }else{

                addressPos = new LatLng(lat,lng);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}
