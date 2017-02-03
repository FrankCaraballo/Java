package com.frank.voicerecognition;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddNewDevice extends Activity {

	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	private Button saveBtn;
	
	public static final String PREFS_NAMEA="porta";
	public static final String PREFS_NAMEB="portb";
	public static final String PREFS_NAMEC="portc";
	public static final String PREFS_NAMED="portd";
	public static final String PREFS_NAMEPLACE="place";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_device);
		
		scanBtn = (Button)findViewById(R.id.scan_button);
		formatTxt = (TextView)findViewById(R.id.scan_format);
		contentTxt = (TextView)findViewById(R.id.scan_content);
		saveBtn = (Button)findViewById(R.id.save);

		final EditText place = (EditText)findViewById(R.id.editText1);
		final EditText porta = (EditText)findViewById(R.id.editText2);
		final EditText portb = (EditText)findViewById(R.id.editText3);
		final EditText portc = (EditText)findViewById(R.id.editText4);
		final EditText portd = (EditText)findViewById(R.id.editText5);
		//listen for clicks
		//scanBtn.setOnClickListener(this);
		
		
		saveBtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				SharedPreferences settingsPlace = getSharedPreferences(PREFS_NAMEPLACE,0);
				SharedPreferences settingsA = getSharedPreferences(PREFS_NAMEA,0);
				SharedPreferences settingsB = getSharedPreferences(PREFS_NAMEB,0);
				SharedPreferences settingsC = getSharedPreferences(PREFS_NAMEC,0);
				SharedPreferences settingsD = getSharedPreferences(PREFS_NAMED,0);
				
				SharedPreferences.Editor editorPlace =settingsPlace.edit();
				SharedPreferences.Editor editorA =settingsA.edit();
				SharedPreferences.Editor editorB =settingsB.edit();
				SharedPreferences.Editor editorC =settingsC.edit();
				SharedPreferences.Editor editorD =settingsD.edit();
				
				
				editorPlace.putString("place",place.getText().toString());
				editorA.putString("porta",porta.getText().toString());
				editorB.putString("portb",portb.getText().toString());
				editorC.putString("portc",portc.getText().toString());
				editorD.putString("portd",portd.getText().toString());
				
				editorPlace.commit();
				editorA.commit();
				editorB.commit();
				editorC.commit();
				editorD.commit();
				
				Intent intent = new Intent(getApplicationContext(),DevicesList.class);
				startActivity(intent);
				
			}
			
			
			
			
			
		});
		
		
			
				
		
		}
	public void onClick(View v){
		//check for scan button
		if(v.getId()==R.id.scan_button){
			//instantiate ZXing integration class
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			//start scanning
			scanIntegrator.initiateScan();
		}
	}

	
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve result of scanning - instantiate ZXing object
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		//check we have a valid result
		if (scanningResult != null) {
			//get content from Intent Result
			String scanContent = scanningResult.getContents();
			//get format name of data scanned
			String scanFormat = scanningResult.getFormatName();
			//output to UI
			formatTxt.setText("FORMAT: "+scanFormat);
			contentTxt.setText("CONTENT: "+scanContent);
		}
		else{
			//invalid scan data or scan canceled
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}



	
}
