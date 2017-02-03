package com.frank.voicerecognition;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Settings extends Activity {

	public static final String PREFS_NAMEA="porta";
	public static final String PREFS_NAMEB="portb";
	public static final String PREFS_NAMEC="portc";
	public static final String PREFS_NAMED="portd";
	public static final String PREFS_NAMEPLACE="place";
	
	private Button clearBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		clearBtn = (Button)findViewById(R.id.clear);

		clearBtn.setOnClickListener(new View.OnClickListener(){

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
				
				
				editorPlace.putString("place","");
				editorA.putString("porta","");
				editorB.putString("portb","");
				editorC.putString("portc","");
				editorD.putString("portd","");
				
				editorPlace.commit();
				editorA.commit();
				editorB.commit();
				editorC.commit();
				editorD.commit();
				
				Toast toast = Toast.makeText(getApplicationContext(),"The settings has been restared" , Toast.LENGTH_LONG);
				toast.show();
				
			}
			
			
			
			
			
		});
		
		
	}


	
	
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			return rootView;
		}
	}
}
