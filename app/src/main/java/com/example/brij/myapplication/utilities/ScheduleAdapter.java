package com.example.brij.myapplication.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brij.myapplication.R;
import com.example.brij.myapplication.model.ScheduleModel;

import java.util.ArrayList;

;

/**
 * Created by Brij on 8/6/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ItemHolder>{


    private ArrayList<ScheduleModel> data;
    ItemClickListener listener;

    public ScheduleAdapter(ArrayList<ScheduleModel> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }



    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.schedule_items, parent, shouldAttachToParentImmediately);
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

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView date;

        ItemHolder(View view){
            super(view);
            date = (TextView)view.findViewById(R.id.date);
//            tittle = (TextView)view.findViewById(R.id.tittle);
//            desc = (TextView)view.findViewById(R.id.desc);
//            date = (TextView)view.findViewById(R.id.date);
//            url = (TextView)view.findViewById(R.id.url);
          view.setOnClickListener(this);
        }

        public void bind(int pos){
//            NewsItem items = data.get(pos);
//            author.setText("Author: "+ items.getAuthor());
//            tittle.setText("Tittle: "+ items.getTittle());
//            desc.setText("Description: "+items.getDescription());
//            url.setText("URL: "+items.getUrl());
//            date.setText("Date: "+items.getDate());

            ScheduleModel items = data.get(pos);
            date.setText("Date:"+items.getGameDate());
        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }

}