package com.example.twilightzone;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twilightzone.adapters.RecycleForPublic;
import com.example.twilightzone.adapters.RecyclerForAdminOldage;
import com.example.twilightzone.buissinessentities.OldageCardDetails;
import com.example.twilightzone.buissinessentities.PubAndSocia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicFragment extends Fragment {
    ArrayList<PubAndSocia> arrayList = new ArrayList<>();

    public PublicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_public, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/public");
        final RecyclerView oldagehomerecycle = (RecyclerView)view.findViewById(R.id.recyclerforpub);
        oldagehomerecycle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        oldagehomerecycle.setLayoutManager(layoutManager);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    PubAndSocia oldageCardDetails = new PubAndSocia(dataSnapshot1.getKey().toString(),dataSnapshot1.child("password").getValue().toString(),dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("address").getValue().toString(),dataSnapshot1.child("email").getValue().toString(),dataSnapshot1.child("contact").getValue().toString());
                    arrayList.add(oldageCardDetails);

                }
                RecycleForPublic playAdapter = new RecycleForPublic(arrayList,getActivity());
                oldagehomerecycle.setAdapter(playAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
