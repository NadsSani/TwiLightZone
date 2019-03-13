package com.example.twilightzone.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twilightzone.OldageHomeDetails;
import com.example.twilightzone.R;
import com.example.twilightzone.buissinessentities.OldageCardDetails;

import java.util.ArrayList;

public class RecyclerViewForOldageInfo extends RecyclerView.Adapter<RecyclerViewForOldageInfo.ViewHolder> {


TextView titles,detail,requests;
ArrayList<OldageCardDetails> list;
Context context;

    public RecyclerViewForOldageInfo (Context context,ArrayList<OldageCardDetails> arrayList){
            this.list = arrayList;
            this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewForOldageInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_to_show_oldage_homes,viewGroup,false);
        return new RecyclerViewForOldageInfo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewForOldageInfo.ViewHolder viewHolder, final int i) {
        final int j;

        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        titles = (TextView)cardView.findViewById(R.id.title);
        detail = (TextView)cardView.findViewById(R.id.details);
        requests = (TextView)cardView.findViewById(R.id.request);
        titles.setText(list.get(i).getTitle().toString());
        detail.setText(list.get(i).getDetails().toString());
        requests.setText(list.get(i).getRequest().toString());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, OldageHomeDetails.class);
              intent.putExtra("userid",list.get(i).getUserid());
              context.startActivity(intent);
            }
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_foroldage);

        }
    }
    @Override
    public int getItemCount() {
        int len = list.size();
        return len ;
    }
}
