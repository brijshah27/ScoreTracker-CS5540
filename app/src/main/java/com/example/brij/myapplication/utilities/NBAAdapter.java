package com.example.brij.myapplication.utilities;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brij.myapplication.Database.Contract;
import com.example.brij.myapplication.R;
import com.example.brij.myapplication.model.NBAData;

import java.util.ArrayList;

/**
 * Created by Brij on 6/26/17.
 */

public class NBAAdapter extends RecyclerView.Adapter<NBAAdapter.ItemHolder> {
    private ArrayList<NBAData> data;
    ItemClickListener listener;
    private Cursor cursor;

    public NBAAdapter(Cursor cursor,ItemClickListener listener){
        //this.data = data;
        this.listener = listener;
        this.cursor=cursor;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.item, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView homeTeam;
        TextView awayTeam;
        TextView homeTeamCity;
        TextView awayTeamCity;
        ImageView vs;
        TextView homeTeamScore;
        TextView awayTeamScore;
        TextView Location;

        ItemHolder(View view){
            super(view);
            homeTeam = (TextView)view.findViewById(R.id.homeTeam);
            awayTeam = (TextView)view.findViewById(R.id.awayTeam);
          //  homeTeamCity = (TextView)view.findViewById(R.id.homeTeamCity);
          //  awayTeamCity = (TextView)view.findViewById(R.id.awayTeamCity);
            vs=(ImageView) view.findViewById(R.id.vs_image);
            homeTeamScore = (TextView)view.findViewById(R.id.homeTeamScore);
            awayTeamScore = (TextView)view.findViewById(R.id.awayTeamScore);
           // Location = (TextView)view.findViewById(R.id.location);
             view.setOnClickListener(this);
        }

        public void bind(int pos){
//            NBAData items = data.get(pos);
//            homeTeam.setText("Home Team: "+ items.getHomeTeam());
//            awayteam.setText("Away Team: "+ items.getAwayTeam());
//            homeTeamCity.setText("Home Team City: "+items.getAwayTeam());
//            awayTeamCity.setText("Away Team City: "+items.getAwayTeamCity());
//            homeTeamScore.setText("Home team score: "+items.getHomeScore());
//            awayTeamScore.setText("Away team score: "+items.getAwayScore());
//            location.setText("Location: "+items.getLocation());

            cursor.moveToPosition(pos);
                    String hometeam=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAM));
            String awayteam= cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAM));
            String hometeamcity=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMETEAMCITY));
            String awayteamcity=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYTEAMCITY));
            String hometeamscore=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_HOMESCORE));
            String awayteamscore=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_AWAYSCORE));
            String location=cursor.getString(cursor.getColumnIndex(Contract.TABLE_GAMES.COLUMN_NAME_LOCATION));

            homeTeam.setText(hometeam);
            awayTeam.setText(awayteam);
            //homeTeamCity.setText("Home Team City :"+hometeamcity);
            //awayTeamCity.setText("Away Team City :"+awayteamcity);
            homeTeamScore.setText(hometeamscore);
            awayTeamScore.setText(awayteamscore);
            //Location.setText("Location is :"+location);


        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
           listener.onItemClick(pos);

        }


//        @Override
//        public void onClick(View v) {
//            int pos = getAdapterPosition();
//            listener.onItemClick(pos);
//        }
    }

}
