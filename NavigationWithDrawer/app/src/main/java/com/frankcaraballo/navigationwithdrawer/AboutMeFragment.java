package com.frankcaraballo.navigationwithdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by frank on 02/07/15.
 */
public class AboutMeFragment extends Fragment {

    public static AboutMeFragment newInstance(){

        AboutMeFragment fragment = new AboutMeFragment();
        return fragment;
    }

    public AboutMeFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_me,container,false);
        return  rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }
}
