package com.frankcaraballo.androidfragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by frank on 01/20/15.
 */
public  class FragmentPortrait extends Fragment {


        @Override
        public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.portrait_fragment,container,false);
        }
}
