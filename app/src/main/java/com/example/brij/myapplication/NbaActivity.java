package com.example.brij.myapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
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

import com.example.brij.myapplication.Database.Contract;
import com.example.brij.myapplication.Database.DBHelper;
import com.example.brij.myapplication.Database.DBUtils;
import com.example.brij.myapplication.Scheduler.SchedulerUtils;
import com.example.brij.myapplication.model.NBAData;
import com.example.brij.myapplication.utilities.NBAAdapter;
import com.example.brij.myapplication.utilities.NetworkUtils;
import com.example.brij.myapplication.utilities.parseJSON;

import java.util.ArrayList;

public class NbaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ArrayList<Void>>, NBAAdapter.ItemClickListener {

    static final String TAG = "NBA activity";

    private TextView errorMessgaeTextView;

    private ProgressBar progressIndicator;

    private  NavigationView navigationView;
    private Cursor cursor;
    private NBAAdapter nbaAdapter2;
    private SQLiteDatabase db;

    private String gameName;

    private static final int LOADER = 2;

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                gameName= null;
            } else {
                gameName= extras.getString("gameName");
            }
        } else {
            gameName= (String) savedInstanceState.getSerializable("gameName");
        }
        Log.d(TAG, "Game name is:"+gameName);

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
        cursor= DBUtils.getAllitems(db);
        nbaAdapter2=new NBAAdapter(cursor,this);




        SchedulerUtils.scheduleRefresh(this);




        progressIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        errorMessgaeTextView = (TextView) findViewById(R.id.error_message_display);
        rv = (RecyclerView) findViewById(R.id.nba_response_result);


        rv.setAdapter(nbaAdapter2);

        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Initialize the scheduler
        DBUtils.deleteNewsitem(db);
        LoaderManager loaderManager=getSupportLoaderManager();
        loaderManager.restartLoader(LOADER,null,this).forceLoad();
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
        int id = item.getItemId();




        if (id == R.id.nav_nba) {
            Intent intent = new Intent(this, NbaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "nba");
            startActivity(intent);

        }
        if(id==R.id.nav_mlb){
            Intent intent = new Intent(this, NbaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "mlb");
            startActivity(intent);

        }
        if(id==R.id.nav_all){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;
            intent.putExtra("gameName", "all");
            startActivity(intent);
        }
        if(id == R.id.nav_live){
            Intent intent = new Intent(this, LiveScoreDemo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;7
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            //intent.putExtra("gameName", "all");
            startActivity(intent);

        }
        if(id == R.id.nav_schedule){
            Intent intent = new Intent(this, ScheduleGames.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            //String gameName = null;7
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            //intent.putExtra("gameName", "all");
            startActivity(intent);

        }

        if(id == R.id.nav_schedule_mlb){
        Intent intent = new Intent(this, ScheduleGamesMLB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        startActivity(intent);

    }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override

            public ArrayList<Void> loadInBackground() {

                try {
                    String jsonNBA = null;

                    if(gameName.equals("nba")) {
                        jsonNBA  = NetworkUtils.getResponseFromHttpUrl();

                    }
                    else if(gameName.equals("mlb")){
                        jsonNBA  = NetworkUtils.getResponseFromHttpUrlMlb();
                    }
                    nba = parseJSON.parseJsonData(NbaActivity.this, jsonNBA);

                    Log.d(TAG,"NBA------------"+jsonNBA);

//                    String jsonMLB = NetworkUtils.getResponseFromHttpUrlMlb();
//                    mlb = parseJSON.parseJsonData(MainActivity.this , jsonMLB);

                    scoreFinal.addAll(nba);
                    //scoreFinal.addAll(mlb);

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

        db=new DBHelper(NbaActivity.this).getReadableDatabase();
        cursor=DBUtils.getAllitems(db);
        nbaAdapter2=new NBAAdapter(cursor,this);
        rv.setAdapter(nbaAdapter2);
        nbaAdapter2.notifyDataSetChanged();


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

//

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
        SchedulerUtils.stopScheduledNewsLoad(this);
        Log.d(TAG, "*********WE'RE HERE*************");
        //Intent detailsIntent = new Intent(this, GameDetails.class);
        //startActivity(detailsIntent);
        cursor.moveToPosition(clickedItemIndex);
// Intent intent=new Intent(this,GameDetails.class);
        String hometeam = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAM));
        String awayteam = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAM));
        String hometeamcity = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAMCITY));
        String awayteamcity = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAMCITY));
        String hometeamscore = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMESCORE));
        String awayteamscore = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYSCORE));
        String location = cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_LOCATION));
        String gameDate=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_DATE));
        findViewById(R.id.nba_response_result).setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("hometeam", hometeam);
        bundle.putString("awayteam",awayteam);
        bundle.putString("hometeamcity",hometeamcity);
        bundle.putString("awayteamcity",awayteamcity);
        bundle.putString("hometeamscore",hometeamscore);
        bundle.putString("awayteamscore",awayteamscore);
        bundle.putString("location",location);
        bundle.putString("gameDate",gameDate);
        NbaFragment fragobj = new NbaFragment();
        fragobj.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMain, fragobj)
                .addToBackStack(null)
                .commit();
    }
}
