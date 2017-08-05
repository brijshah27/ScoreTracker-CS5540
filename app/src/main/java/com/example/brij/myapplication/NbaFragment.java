package com.example.brij.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Brij on 8/2/17.
 */

public class NbaFragment extends Fragment {


    private TextView debug;


    public NbaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.nba_fragment, container, false);
        debug = (TextView) view.findViewById(R.id.debug);
        String strtext = getArguments().getString("hometeam");
       // return inflater.inflate(R.layout.fragment, container, false);
        debug.setText(strtext);
        return view;
    }

}
