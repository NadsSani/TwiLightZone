package com.example.twilightzone;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twilightzone.adapters.RecyclerForAdminOldage;
import com.example.twilightzone.adapters.RecyclerViewForOldageInfo;
import com.example.twilightzone.buissinessentities.OldageCardDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OldageFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/oldagehomes");
    ArrayList<OldageCardDetails> arrayList = new ArrayList<>();
    Context context;
    public OldageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/oldagehomes");

        View view = inflater.inflate(R.layout.fragment_oldage, container, false);
        final RecyclerView oldagehomerecycle = (RecyclerView)view.findViewById(R.id.recyclerforoldage);

        oldagehomerecycle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        oldagehomerecycle.setLayoutManager(layoutManager);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    OldageCardDetails oldageCardDetails = new OldageCardDetails(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("details").getValue().toString(),dataSnapshot1.child("request").getValue().toString(),dataSnapshot1.getKey().toString());
                    arrayList.add(oldageCardDetails);

                }
                RecyclerForAdminOldage playAdapter = new RecyclerForAdminOldage(arrayList,getActivity());
                oldagehomerecycle.setAdapter(playAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return view;

    }

}
