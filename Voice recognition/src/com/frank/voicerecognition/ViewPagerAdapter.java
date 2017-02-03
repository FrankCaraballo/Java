package com.frank.voicerecognition;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	int [] image = {R.drawable.diapositiva1,R.drawable.diapositiva2,R.drawable.diapositiva3,R.drawable.diapositiva4};
	
	
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public Fragment getItem(int possition) {
		// TODO Auto-generated method stub
		return HelpViewPagerFragment.newInstance(possition,image[possition]);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return image.length;
	}

}
