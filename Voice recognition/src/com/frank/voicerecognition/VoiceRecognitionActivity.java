package com.frank.voicerecognition;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class VoiceRecognitionActivity extends Activity {
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;

	private EditText metTextHint;
	private ListView mlvTextMatches;
	private Spinner msTextMatches;
	private ImageButton mbtSpeak;

	public  String state="";
	public  String state2="";
	public  String state3="";
	public  String state4="";
	public  String state5="";
	public  String state6="";
	public  String state7="";
	public  String state8="";
	final String myTag = "DocsUpload";

	
	public static final String PREFS_NAMEA="porta";
	public static final String PREFS_NAMEB="portb";
	public static final String PREFS_NAMEC="portc";
	public static final String PREFS_NAMED="portd";
	public static final String PREFS_NAMEPLACE="place";
	
	public String  device0;
	public String  device1;
	public String  device2;
	public String  device3;
	public String  place;
	
	MenuDrawer mDrawer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer = MenuDrawer.attach(VoiceRecognitionActivity.this);
		mDrawer.setMenuView(R.layout.menu_drawer);
		mDrawer.setContentView(R.layout.activity_voice_recognition);
		
		
		SharedPreferences settingsPlace = getSharedPreferences(PREFS_NAMEPLACE,0);
		SharedPreferences settingsA = getSharedPreferences(PREFS_NAMEA,0);
		SharedPreferences settingsB = getSharedPreferences(PREFS_NAMEB,0);
		SharedPreferences settingsC = getSharedPreferences(PREFS_NAMEC,0);
		SharedPreferences settingsD = getSharedPreferences(PREFS_NAMED,0);
		
		
		place  = settingsPlace.getString("place","place").toString();
		device0= settingsA.getString("porta","porta").toString();
		device1= settingsB.getString("portb","portb").toString();
		device2= settingsC.getString("portc","portc").toString();
		device3= settingsD.getString("portd","portd").toString();
		
		//metTextHint = (EditText) findViewById(R.id.etTextHint);
		//mlvTextMatches = (ListView) findViewById(R.id.lvTextMatches);
		//msTextMatches = (Spinner) findViewById(R.id.sNoOfMatches);
		mbtSpeak = (ImageButton ) findViewById(R.id.btSpeak);
		
		Log.i(myTag, "OnCreate()");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				postData();
				
			}
		});
		t.start();
	}
	
	public void AddNew (View v){
		Intent intent = new Intent(getApplicationContext(),AddNewDevice.class);
		startActivity(intent);
		
	}
	
	public void Devices (View v){
		Intent intent = new Intent(getApplicationContext(),DevicesList.class);
		startActivity(intent);
		
	}
	public void Settings (View v){
		Intent intent = new Intent(getApplicationContext(),Settings.class);
		startActivity(intent);
		
	}
	public void Help (View v){
		Intent intent = new Intent(getApplicationContext(),Help.class);
		startActivity(intent);
		
	}
	public void About (View v){
		Intent intent = new Intent(getApplicationContext(),About.class);
		startActivity(intent);
		
	}
	
	
	public void postData() {
		

		String fullUrl = "https://docs.google.com/forms/d/1F1HysX-2iHXijp4ETTj49pe96Asv8Tef7mt8Dpbf4Ts/formResponse?edit=ChMzMzg2MTQxODM1NjY0MDQ4MTYwEOnpg9_hsb_vGA";
		HttpRequest mReq = new HttpRequest();
		String col1 = state;
		String col2 = state2;
		String col3 = state3;
		String col4 = state4;
		String col5 = state5;
		String col6 = state6;
		String col7 = state7;
		String col8 = state8;
		String col9 = "0";
		String col10 = "0";
		String col11 = "0";
		String col12 = "0";
		String col13 = "0";
		String col14 = "0";
		String col15 = "0";
		String col16 = "0";
		
		String data = "entry_1476640692=" + URLEncoder.encode(col1) + "&" + 
					  "entry_129076248=" + URLEncoder.encode(col2)  + "&" + 
					  "entry_23872831=" + URLEncoder.encode(col3)  + "&" +
					  "entry_1961304732=" + URLEncoder.encode(col4)  + "&" + 
					  "entry_48247695=" + URLEncoder.encode(col5)  + "&" +
					  "entry_2111280530=" + URLEncoder.encode(col6)  + "&" + 
					  "entry_512405949=" + URLEncoder.encode(col7)  + "&" +
					  "entry_13632549=" + URLEncoder.encode(col8)  + "&" + 
					  "entry_1814024711=" + URLEncoder.encode(col9)  + "&" +
					  "entry_758180800=" + URLEncoder.encode(col10)  + "&" + 
					  "entry_186312392=" + URLEncoder.encode(col11)  + "&" +
					  "entry_454371807=" + URLEncoder.encode(col12)  + "&" + 
					  "entry_1552039808=" + URLEncoder.encode(col13)  + "&" +
					  "entry_1615547057=" + URLEncoder.encode(col14)  + "&" + 
					  "entry_1418676492=" + URLEncoder.encode(col15)  + "&" +
					  "entry_289952943=" + URLEncoder.encode(col16)  ;
		String response = mReq.sendPost(fullUrl, data);
		Log.i(myTag, response);
	} 
	
	
	public void checkVoiceRecognition() {
		// Check if voice recognition is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
			mbtSpeak.setEnabled(false);
			Toast.makeText(this, "Voice recognizer not present",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void speak(View view) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		// Specify the calling package to identify your application
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
				.getPackage().getName());

		// Display an hint to the user about what he should say.
		//intent.putExtra(RecognizerIntent.EXTRA_PROMPT, metTextHint.getText()
			//	.toString());

		// Given an hint to the recognizer about what the user is going to say
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

		// If number of Matches is not selected then return show toast message
		/*if (msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
			Toast.makeText(this, "Please select No. of Matches from spinner",
					Toast.LENGTH_SHORT).show();
			return;
		} */

		//int noOfMatches = Integer.parseInt(msTextMatches.getSelectedItem()
		//		.toString());
		// Specify how many results you want to receive. The results will be
		// sorted where the first result is the one with higher confidence.

		//intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);

		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE)

			//If Voice recognition is successful then it returns RESULT_OK
			if(resultCode == RESULT_OK) {

				ArrayList<String> textMatchList = data
				.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				
				
				

				
				state =textMatchList.get(0);
				state3 =device0;
				state4=device1;
				state5=device3;
				state6=place;
				
				
				/*if(textMatchList.get(0)=="invoice on"||textMatchList.get(0)=="on" )
				{
					state = "H";					
				} 
				else if(textMatchList.get(0)=="invoice off" || textMatchList.get(0)=="off")
				{
					state = "L";
				}*/
				
				 if(textMatchList.get(0).toLowerCase().equals("on"))
				{
					state2 = "A";
				}
				else if(textMatchList.get(0).toLowerCase().equals("off"))
				{
					state2 = "a";
				}else if(textMatchList.get(0).toLowerCase().equals("port a on"))
				{
					state2 = "A";
				}else if(textMatchList.get(0).toLowerCase().equals("port a off"))
				{
					state2 = "a";
				}else if(textMatchList.get(0).toLowerCase().equals("port b on"))
				{
					state2 = "B";
				}else if(textMatchList.get(0).toLowerCase().equals("port b off"))
				{
					state2 = "b";
				}else if(textMatchList.get(0).toLowerCase().equals("port c on"))
				{
					state2 = "C";
				}else if(textMatchList.get(0).toLowerCase().equals("port c off"))
				{
					state2 = "c";
				}else if(textMatchList.get(0).toLowerCase().equals("port a on"))
				{
					state2 = "D";
				}else if(textMatchList.get(0).toLowerCase().equals("port a off"))
				{
					state2 = "d";
				}
				else if(textMatchList.get(0).toLowerCase().equals("invoice new device"))
				{
					
						Intent intent = new Intent(getApplicationContext(),AddNewDevice.class);
						startActivity(intent);		
			
				}else if(textMatchList.get(0).toLowerCase().equals("new device"))
				{
					
					Intent intent = new Intent(getApplicationContext(),AddNewDevice.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("invoice help"))
				{
					
					Intent intent = new Intent(getApplicationContext(),Help.class);
					startActivity(intent);		
		
				}
				else if(textMatchList.get(0).toLowerCase().equals("help"))
				{
					
					Intent intent = new Intent(getApplicationContext(),Help.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).equals("invoice devices"))
				{
					
					Intent intent = new Intent(getApplicationContext(),DevicesList.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("devices"))
				{
					
					Intent intent = new Intent(getApplicationContext(),DevicesList.class);
					startActivity(intent);		
		
				}
				else if(textMatchList.get(0).toLowerCase().equals("invoice settings"))
				{
					
					Intent intent = new Intent(getApplicationContext(),Settings.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("settings"))
				{
					
					Intent intent = new Intent(getApplicationContext(),Settings.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("invoice about"))
				{
					
					Intent intent = new Intent(getApplicationContext(),About.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("about"))
				{
					
					Intent intent = new Intent(getApplicationContext(),About.class);
					startActivity(intent);		
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on " + device0))
				{
					
					state2="A";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off " + device0))
				{
					
					state2="a";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on " + device1))
				{
					
					state2="B";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off " + device1))
				{
					
					state2="b";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on " + device2))
				{
					
					state2="C";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off " + device2))
				{
					
					state2="c";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on " + device3))
				{
					
					state2="D";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off " + device3))
				{
					
					state2="d";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device0 + "on the " + place))
				{
					
					state2="A";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device0+ "on the " + place))
				{
					
					state2="a";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device1+ "on the " + place))
				{
					
					state2="B";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device1+ "on the " + place))
				{
					
					state2="b";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device2+ "on the " + place))
				{
					
					state2="C";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device2+ "on the " + place))
				{
					
					state2="c";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device3+ "on the " + place))
				{
					
					state2="D";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device3+ "on the " + place))
				{
					
					state2="d";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device0))
				{
					
					state2="A";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device0))
				{
					
					state2="a";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device1))
				{
					
					state2="B";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device1))
				{
					
					state2="b";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device2))
				{
					
					state2="C";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device2))
				{
					
					state2="c";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device3))
				{
					
					state2="D";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device3))
				{
					
					state2="d";	
		
				}else if(textMatchList.get(0).toLowerCase().equals(device0))
				{
					
					state2="A";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device0))
				{
					
					state2="a";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device1))
				{
					
					state2="B";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device1))
				{
					
					state2="b";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device2))
				{
					
					state2="C";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device2))
				{
					
					state2="c";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn on the " + device0))
				{
					
					state2="D";	
		
				}else if(textMatchList.get(0).toLowerCase().equals("turn off the " + device0))
				{
					
					state2="d";	
		
				}
					
				
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						postData();
						
					}
				});
				t.start();
				
				/*if (!textMatchList.isEmpty()) {
					// If first Match contains the 'search' word
					// Then start web search.
					if (textMatchList.get(0).contains("search")) {

						String searchQuery = textMatchList.get(0).replace("search",
						" ");
						Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
						search.putExtra(SearchManager.QUERY, searchQuery);
						startActivity(search);
					} else {
						// populate the Matches
						mlvTextMatches
						.setAdapter(new ArrayAdapter<String>(this,
								android.R.layout.simple_list_item_1,
								textMatchList));
					}

				}*/
			//Result code for various error.	
			//}else 
			}if(resultCode == RecognizerIntent.RESULT_AUDIO_ERROR){
				showToastMessage("Audio Error");
			}else if(resultCode == RecognizerIntent.RESULT_CLIENT_ERROR){
				showToastMessage("Client Error");
			}else if(resultCode == RecognizerIntent.RESULT_NETWORK_ERROR){
				showToastMessage("Network Error");
			}else if(resultCode == RecognizerIntent.RESULT_NO_MATCH){
				showToastMessage("No Match");
			}else if(resultCode == RecognizerIntent.RESULT_SERVER_ERROR){
				showToastMessage("Server Error");
			}
		super.onActivityResult(requestCode, resultCode, data);
	}
	void showToastMessage(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
