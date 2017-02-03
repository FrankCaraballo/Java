package com.frank.voicerecognition;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DevicesList extends Activity {

	public static final String PREFS_NAMEA="porta";
	public static final String PREFS_NAMEB="portb";
	public static final String PREFS_NAMEC="portc";
	public static final String PREFS_NAMED="portd";
	public static final String PREFS_NAMEPLACE="place";
	public static final String PREFS_TITLE_DEVICE="dvlist";
	
	String val0 = "";
	String val1 = "";
	String val2 = "";
	String val3 = "";
	
	String[]data={val0,val1,val2,val3};
	ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devices_list);
	
		SharedPreferences settingsPlace = getSharedPreferences(PREFS_NAMEPLACE,0);
		SharedPreferences settingsA = getSharedPreferences(PREFS_NAMEA,0);
		SharedPreferences settingsB = getSharedPreferences(PREFS_NAMEB,0);
		SharedPreferences settingsC = getSharedPreferences(PREFS_NAMEC,0);
		SharedPreferences settingsD = getSharedPreferences(PREFS_NAMED,0);
		SharedPreferences settingsdv = getSharedPreferences(PREFS_TITLE_DEVICE,0);
		
		val0 = settingsA.getString("porta","porta").toString();
		val1 = settingsB.getString("portb","portB").toString();
		val2 = settingsC.getString("portc","portc").toString();
		val3 = settingsD.getString("portd","portd").toString();
		
		String[]data={val0,val1,val2,val3};
		ListView l;
		
		TextView show = (TextView)findViewById(R.id.textView1);
		
		show.setText(settingsdv.getString("dvlist","Device List"));
		
		l =(ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView1,data);
		
		l.setAdapter(adapter);
		
		}
	}



