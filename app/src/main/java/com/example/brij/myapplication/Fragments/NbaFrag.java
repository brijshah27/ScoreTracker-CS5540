package com.example.brij.myapplication.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brij.myapplication.Database.DBHelper;
import com.example.brij.myapplication.Database.DBUtils;
import com.example.brij.myapplication.R;
import com.example.brij.myapplication.utilities.NBAAdapter;

/**
 * Created by daminshah on 8/3/17.
 */

public class NbaFrag extends Fragment {

    private DBHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private TextView nbalist;
    RecyclerView rv;
    NBAAdapter adapter;

    private final String TAG = "myListFRAGMENT";

    public NbaFrag() {
    }

    public static NbaFrag newInstance() {
        NbaFrag fragment = new NbaFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nbalist, container, false);
        nbalist=(TextView)view.findViewById(R.id.nba_list);
        Log.d(TAG,"INSIDE NBA FRAGMENT");



        rv=(RecyclerView)view.findViewById(R.id.nba_response_result);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
     //   rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        createAdapter();
    }

    private void createAdapter(){
        //Database is set up
        helper = new DBHelper(getActivity());
        db = helper.getWritableDatabase();

        cursor= DBUtils.getAllitems(db);
        //Adapter is made using the cursor that was set above.
       // adapter = new NBAAdapter(cursor,new NBAAdapter.ItemClickList

        rv.setAdapter(adapter);
    }


}
