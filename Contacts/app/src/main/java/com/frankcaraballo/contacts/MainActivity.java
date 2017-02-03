package com.frankcaraballo.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    SQLiteDatabase contactsDB = null;

    Button createDBButton,addContactButton,deleteContactButton,getContactButton,deleteDBButton;

    EditText nameEditText,emailEditText,contactListEditText,idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDBButton = (Button) findViewById(R.id.createDBButton);
        addContactButton = (Button) findViewById(R.id.addContactButton);
        deleteContactButton = (Button) findViewById(R.id.deleteContactButton);
        getContactButton = (Button) findViewById(R.id.getContactsButton);
        deleteDBButton = (Button) findViewById(R.id.deleteDBButton);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        contactListEditText = (EditText) findViewById(R.id.contactListText);
        idEditText = (EditText) findViewById(R.id.idEditText);


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

    public void createDatabase(View view) {

        try{

            contactsDB = this.openOrCreateDatabase("MyContacts",MODE_PRIVATE,null);

            contactsDB.execSQL("CREATE TABLE IF NOT EXISTS contacts" + "(id integer primary key, name VARCHAR, email VARCHAR);");

            File dataBase = getApplicationContext().getDatabasePath("MyContacts.db");

            if(dataBase.exists()){

                Toast.makeText(this,"Database Created",Toast.LENGTH_SHORT).show();

            } else{

                Toast.makeText(this,"Database Missing",Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){

            Log.e("CONTACTS ERROR", "Error creating Database");
        }

        addContactButton.setClickable(true);
        deleteContactButton.setClickable(true);
        getContactButton.setClickable(true);
        deleteDBButton.setClickable(true);

    }

    public void addContact(View view) {


        String contactName = nameEditText.getText().toString();
        String contactEmail = emailEditText.getText().toString();

        contactsDB.execSQL("INSERT INTO contacts (name, email) VALUES('" + contactName +"','" +contactEmail + "');");

    }

    public void deleteContact(View view) {

        String id = idEditText.getText().toString();

        contactsDB.execSQL("DELETE FROM contacts WHERE id=" + id +";");
    }

    public void getContacts(View view) {

        Cursor cursor = contactsDB.rawQuery("SELECT *FROM contacts", null);

        int idColum = cursor.getColumnIndex("id");
        int nameColum = cursor.getColumnIndex("name");
        int emailColum = cursor.getColumnIndex("email");

        cursor.moveToFirst();

        String contactList = "";

        if(cursor != null && (cursor.getCount() > 0)){

            do{

                String id = cursor.getString(idColum);

                String name = cursor.getString(nameColum);

                String email = cursor.getString(emailColum);

                contactList = contactList + id + " : " + name + " : " + email + "\n";

            }while (cursor.moveToNext());

            contactListEditText.setText(contactList);

        }else{

            Toast.makeText(this,"No Results to Show", Toast.LENGTH_SHORT).show();

            contactListEditText.setText("");



        }
    }

    public void deleteDatabase(View view) {

        this.deleteDatabase("MyContacts");
    }

    @Override
    protected void onDestroy() {

        contactsDB.close();

        super.onDestroy();
    }
}
