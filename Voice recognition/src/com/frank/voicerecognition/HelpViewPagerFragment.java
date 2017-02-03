package com.frank.voicerecognition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HelpViewPagerFragment extends Fragment {
	
private static final String POSSITION = "possition";
private static final String IMAGEID = "imageId";


public HelpViewPagerFragment() {
	
}
		public static HelpViewPagerFragment newInstance (int possition, int imageId){
			
			HelpViewPagerFragment myfrag = new HelpViewPagerFragment();
			
			Bundle bundle = new Bundle();
			bundle.putInt(POSSITION,possition);
			bundle.putInt(IMAGEID,imageId);
			myfrag.setArguments(bundle);
			
			
			return myfrag;
		} 
	

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_help, container,
					false);
			
			
			int imageId =getArguments().getInt(IMAGEID);
			
			
			ImageView iv = (ImageView)rootView.findViewById(R.id.ivViewPager);
			iv.setImageResource(imageId);
			
			
			return rootView;
		}
	

}
