package com.example.twilightzone.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.twilightzone.OldageHomeDetails;
import com.example.twilightzone.buissinessentities.OldageCardDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerForAdminOldage extends  RecyclerView.Adapter<RecyclerForAdminOldage.ViewHolder> {
       Button reject;
    TextView titles,detail,requests;
    ArrayList<OldageCardDetails> list;
    Context context;

    public RecyclerForAdminOldage(ArrayList<OldageCardDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }








    @NonNull
    @Override
    public RecyclerForAdminOldage.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_for_admin_oldage,viewGroup,false);
        return new RecyclerForAdminOldage.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerForAdminOldage.ViewHolder viewHolder,final int i) {

        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        titles = (TextView)cardView.findViewById(R.id.titleadmin);
        detail = (TextView)cardView.findViewById(R.id.detailsadmin);
        requests = (TextView)cardView.findViewById(R.id.requestadmin);
        reject = (Button)cardView.findViewById(R.id.reject);
        titles.setText(list.get(i).getTitle().toString());
        detail.setText(list.get(i).getDetails().toString());
        requests.setText("requests: " + list.get(i).getRequest().toString());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OldageHomeDetails.class);
                intent.putExtra("userid",list.get(i).getUserid());
                context.startActivity(intent);
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users/oldagehomes/"+list.get(i).getUserid());
                myRef.removeValue();
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_foradminoldage);

        }
    }
}
