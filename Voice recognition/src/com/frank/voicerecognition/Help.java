package com.frank.voicerecognition;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class Help extends FragmentActivity {

	ViewPager pager;
	ViewPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		
		pager = (ViewPager)findViewById(R.id.pager);
		adapter= new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		
		
	}

	

	
}
