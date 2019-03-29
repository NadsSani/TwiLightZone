package com.example.twilightzone.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.twilightzone.R;
import com.example.twilightzone.buissinessentities.PubAndSocia;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecycleForSoc  extends RecyclerView.Adapter<RecycleForSoc.ViewHolder> {
    Button reject;
    TextView title,address,contact,email;
    ArrayList<PubAndSocia> list;
    Context context;


    public RecycleForSoc(ArrayList<PubAndSocia> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecycleForSoc.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_pub_social,viewGroup,false);
        return new RecycleForSoc.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleForSoc.ViewHolder viewHolder, final int i) {

        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        title = (TextView)cardView.findViewById(R.id.titlepub);
        address = (TextView)cardView.findViewById(R.id.address);
        contact = (TextView)cardView.findViewById(R.id.contact);
        email = (TextView)cardView.findViewById(R.id.email);
        title.setText(list.get(i).getName());
        address.setText(list.get(i).getAddress());
        contact.setText(list.get(i).getContact());
        reject = (Button)cardView.findViewById(R.id.rejectpub);
        email.setText(list.get(i).getEmail());
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users/socialgroups/"+list.get(i).getUserid());
                myRef.removeValue();
            }
        });


    }


    @Override
    public int getItemCount() {
        int len = list.size();
        return len ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_forpub);

        }
    }













}
