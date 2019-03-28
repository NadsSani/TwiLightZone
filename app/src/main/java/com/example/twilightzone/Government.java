package com.example.twilightzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.twilightzone.adapters.RecyclerViewForOldageInfo;
import com.example.twilightzone.buissinessentities.OldageCardDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Government extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
/*
  1.view request from oldage home.
  2.view the details of oldage home.
  3.notification of approval
  */
ImageButton menubutton;
SharedPreferences sharedPreferences;
ArrayList<OldageCardDetails> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/oldagehomes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government);
        arrayList = new ArrayList<>();
        final RecyclerView oldagehomerecycle = (RecyclerView)findViewById(R.id.recyclerforgov);
        oldagehomerecycle.setHasFixedSize(true);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String namefortitle = sharedPreferences.getString("userid","");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        oldagehomerecycle.setLayoutManager(layoutManager);
        menubutton = (ImageButton)findViewById(R.id.menubutton);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    OldageCardDetails oldageCardDetails = new OldageCardDetails(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("details").getValue().toString(),dataSnapshot1.child("request").getValue().toString(),dataSnapshot1.getKey().toString());
                    arrayList.add(oldageCardDetails);

                }
                RecyclerViewForOldageInfo playAdapter = new RecyclerViewForOldageInfo(Government.this,arrayList);
                oldagehomerecycle.setAdapter(playAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(Government.this, v);

                MenuInflater inflater = popup.getMenuInflater();
                popup.setOnMenuItemClickListener(Government.this);
                inflater.inflate(R.menu.menuf, popup.getMenu());
                popup.show();



            }
        });



    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Government.this, MainActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.helpline:
                Intent intent12 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+919633107599", null));
                startActivity(intent12);
                return  true;

            default:
                return false;
        }
    }



}
