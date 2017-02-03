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
public class ContactInfoFragment extends Fragment{

    public static ContactInfoFragment newInstance(){

        ContactInfoFragment fragment = new ContactInfoFragment();
        return fragment;
    }

    public ContactInfoFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_info,container,false);
        return  rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(2);
    }
}
