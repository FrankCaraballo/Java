package com.frankcaraballo.listview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] favoriteTVShows={"Naruto","One Piece","Bleach","Suits",
                "The Big Bang Theory","How It Is Made","Through the Wormhole","PewDiePie"};

        ListAdapter theAdapter = new MyAdapter (this,favoriteTVShows);
        /*ListAdapter theAdapter = new ArrayAdapter<String>(this,R.layout.row_layout,R.id.textView1,
                favoriteTVShows);*/
        /*ListAdapter theAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                favoriteTVShows);*/

        ListView theListView =(ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new
                                                   AdapterView.OnItemClickListener() {
                                                       @Override
                                                       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                                                           String tvShowPicked = "You Selected " + String.valueOf(adapterView.getItemAtPosition(position));

                                                           Toast.makeText(MainActivity.this,tvShowPicked,Toast.LENGTH_LONG).show();
                                                       }
                                                   });
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
}
