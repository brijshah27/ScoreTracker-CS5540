package com.example.brij.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.brij.myapplication.Database.DBHelper;
import com.example.brij.myapplication.Database.DBUtils;
import com.example.brij.myapplication.Fragments.NbaFrag;
import com.example.brij.myapplication.Scheduler.SchedulerUtils;
import com.example.brij.myapplication.model.NBAData;
import com.example.brij.myapplication.utilities.NBAAdapter;
import com.example.brij.myapplication.utilities.NetworkUtils;
import com.example.brij.myapplication.utilities.parseJSON;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener , LoaderManager.LoaderCallbacks<ArrayList<Void>>, NBAAdapter.ItemClickListener{

    static final String TAG = "mainactivity";

    private TextView errorMessgaeTextView;

    private ProgressBar progressIndicator;


    private Cursor cursor;
    private NBAAdapter nbaAdapter;
    private SQLiteDatabase db;

    private static final int LOADER = 1;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db=new DBHelper(this).getReadableDatabase();
        cursor=DBUtils.getAllitems(db);
        nbaAdapter=new NBAAdapter(cursor,this);




        SchedulerUtils.scheduleRefresh(this);




        progressIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        errorMessgaeTextView = (TextView) findViewById(R.id.error_message_display);
        rv = (RecyclerView) findViewById(R.id.nba_response_result);

        rv.setAdapter(nbaAdapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

    }





    @Override protected void onStart() {
        super.onStart();
        //Initialize the scheduler
        SchedulerUtils.scheduleRefresh(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SchedulerUtils.stopScheduledNewsLoad(this);
    }

    private void showErrorMessage() {

        errorMessgaeTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);

        Fragment frag=null;
        switch (item.getItemId())
        {
            case R.id.nav_nbs:
                Log.d(TAG,"MAIN ACTIVITY-NBA LISTS");
                frag= NbaFrag.newInstance();
                break;

            default:
                Log.e(TAG,"Navigation Error Occurred");
                Log.e(TAG,"Navigation ID:" +item.getItemId());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_view, frag);
        transaction.commit();
       return true;
    }

    @Override
    public Loader<ArrayList<Void>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Void>>(this) {


            @Override
            public void onStartLoading() {
                super.onStartLoading();


                progressIndicator.setVisibility(View.VISIBLE);

            }

            ArrayList<NBAData> nba= null;
            ArrayList<NBAData> mlb= null;

            ArrayList<NBAData> scoreFinal= new ArrayList<>();
            @Override

            public ArrayList<Void> loadInBackground() {

                    try {
                        String jsonNBA = NetworkUtils.getResponseFromHttpUrl();
                        nba = parseJSON.parseJsonData(MainActivity.this , jsonNBA);

                        Log.d(TAG,"NBA------------"+jsonNBA);

                        String jsonMLB = NetworkUtils.getResponseFromHttpUrlMlb();
                        mlb = parseJSON.parseJsonData(MainActivity.this , jsonMLB);

                        scoreFinal.addAll(nba);
                        scoreFinal.addAll(mlb);

                        db=new DBHelper(this.getContext()).getWritableDatabase();
                        DBUtils.insertnews(db,scoreFinal);



                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Void>> loader, ArrayList<Void> data) {


        progressIndicator.setVisibility(View.INVISIBLE);
        errorMessgaeTextView.setVisibility(View.INVISIBLE);

        db=new DBHelper(MainActivity.this).getReadableDatabase();
        cursor=DBUtils.getAllitems(db);
        nbaAdapter=new NBAAdapter(cursor,this);
        rv.setAdapter(nbaAdapter);
        nbaAdapter.notifyDataSetChanged();


//
//        if (data!=null) {
//            Log.d(TAG, "g");
//            NBAAdapter adapter = new NBAAdapter(data);
//            rv.setAdapter(adapter);
//                NBAAdapter adapternhl = new NBAAdapter(newsNhl);
//                rv.setAdapter(adapternhl);

//        } else {
//            showErrorMessage();

       // }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Void>> loader) {

    }

//    public class NewsTask extends AsyncTask<String, Void, ArrayList<NBAData>> {
//
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressIndicator.setVisibility(View.VISIBLE);
//
//
//        }
//
//
//
//        @Override
//        protected ArrayList<NBAData> doInBackground(String... params) {
//            ArrayList<NBAData> nba= null;
//            ArrayList<NBAData> mlb= null;
//
//            //New arrayList to keep all scores togather.
//            ArrayList<NBAData> scoreFinal= new ArrayList<>();
//
//           // URL newsURL = NetworkUtils.buildUrl();
//            //Log.d(TAG, "url: " + newsURL.toString());
//
//            try {
//                //two calls for diffrent apis
//
//                //NBA api call
//                String jsonNBA = NetworkUtils.getResponseFromHttpUrl();
//                nba = parseJSON.parseJsonData(MainActivity.this , jsonNBA);
//
//                //MLB api call
//                String jsonMLB = NetworkUtils.getResponseFromHttpUrlMlb();
//                mlb = parseJSON.parseJsonData(MainActivity.this , jsonMLB);
//
//
//                //adding all api results.
//                scoreFinal.addAll(nba);
//                scoreFinal.addAll(mlb);;
//            }
//             catch(JSONException e) {
//                e.printStackTrace();
//            }
//            return scoreFinal;
//
//        }
//
//        @Override
//        protected void onPostExecute(final ArrayList<NBAData> data) {
//            super.onPostExecute(data);
//            progressIndicator.setVisibility(View.INVISIBLE);
//            errorMessgaeTextView.setVisibility(View.INVISIBLE);
//            if (data!=null) {
//                Log.d(TAG, "g");
//                NBAAdapter adapter = new NBAAdapter(data);
//                        rv.setAdapter(adapter);
////                NBAAdapter adapternhl = new NBAAdapter(newsNhl);
////                rv.setAdapter(adapternhl);
//
//            } else {
//                showErrorMessage();
//
//            }
//            //return data;
//        }
//
//    }

//    public void openWebPage(String url) {
//        Uri webpage = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == R.id.action_search) {
          LoaderManager loaderManager=getSupportLoaderManager();
            loaderManager.restartLoader(LOADER,null,this).forceLoad();
        }

        return true;
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
            Log.d(TAG,"In onItemCLiCK--------" +clickedItemIndex);
    }
}
