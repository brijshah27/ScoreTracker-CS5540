package com.example.brij.myapplication.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brij.myapplication.R;
import com.example.brij.myapplication.model.NBAData;

import java.util.ArrayList;

/**
 * Created by Brij on 6/26/17.
 */

public class NBAAdapter extends RecyclerView.Adapter<NBAAdapter.ItemHolder> {
    private ArrayList<NBAData> data;
    //ItemClickListener listener;

    public NBAAdapter(ArrayList<NBAData> data){
        this.data = data;
        //this.listener = listener;
    }

//    public interface ItemClickListener {
//        void onItemClick(int clickedItemIndex);
//    }

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
        return data.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{


        TextView homeTeam;
        TextView awayteam;
        TextView homeTeamCity;
        TextView awayTeamCity;
        TextView homeTeamScore;
        TextView awayTeamScore;
        TextView location;

        ItemHolder(View view){
            super(view);
            homeTeam = (TextView)view.findViewById(R.id.homeTeam);
            awayteam = (TextView)view.findViewById(R.id.awayTeam);
            homeTeamCity = (TextView)view.findViewById(R.id.homeTeamCity);
            awayTeamCity = (TextView)view.findViewById(R.id.awayTeamCity);
            homeTeamScore = (TextView)view.findViewById(R.id.homeTeamCity);
            awayTeamScore = (TextView)view.findViewById(R.id.awayTeamScore);
            location = (TextView)view.findViewById(R.id.location);
            //view.setOnClickListener(this);
        }

        public void bind(int pos){
            NBAData items = data.get(pos);
            homeTeam.setText("Home Team: "+ items.getHomeTeam());
            awayteam.setText("Away Team: "+ items.getAwayTeam());
            homeTeamCity.setText("Home Team City: "+items.getAwayTeam());
            awayTeamCity.setText("Away Team City: "+items.getAwayTeamCity());
            homeTeamScore.setText("Home team score:: "+items.getHomeScore());
            awayTeamScore.setText("Away team score:"+items.getAwayScore());
            location.setText("Location"+items.getLocation());

        }


//        @Override
//        public void onClick(View v) {
//            int pos = getAdapterPosition();
//            listener.onItemClick(pos);
//        }
    }

}
