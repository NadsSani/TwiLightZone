package com.example.twilightzone;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.twilightzone.adapters.RecyclerViewForOldageInfo;
import com.example.twilightzone.buissinessentities.OldageCardDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SocialGroups extends AppCompatActivity {
/*
* edit details
* viewing request
* receiving notification
*
*
*
*
*
* */

    ArrayList<OldageCardDetails> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/oldagehomes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_groups);
        final RecyclerView oldagehomerecycle = (RecyclerView)findViewById(R.id.recyclerforsocial);
        oldagehomerecycle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        oldagehomerecycle.setLayoutManager(layoutManager);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    OldageCardDetails oldageCardDetails = new OldageCardDetails(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("details").getValue().toString(),dataSnapshot1.child("request").getValue().toString(),dataSnapshot1.getKey().toString());
                    arrayList.add(oldageCardDetails);
                }
                RecyclerViewForOldageInfo playAdapter = new RecyclerViewForOldageInfo(SocialGroups.this,arrayList);
                oldagehomerecycle.setAdapter(playAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
