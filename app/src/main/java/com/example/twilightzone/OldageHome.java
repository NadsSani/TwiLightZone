package com.example.twilightzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twilightzone.adapters.PagerAdapterForViewPager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OldageHome extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
/*
*edit details
* reject request
* sending request
* receiving notification from donors
*
*
*
*
*
*
*
*
*
*
*
* */
Button accepted,acceptbutforgov;
    SharedPreferences sharedPreferences;
    ImageButton menubutton;
    SharedPreferences.Editor editor;
    TextView title ,description,register,emailid,contact,daycare,oldage,membercount,income,address,requestforpublic,govrequest;
    final ArrayList<String> arrayList = new ArrayList<>();
    private static final String TAG = "Policr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldage_home);
        title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        register = (TextView)findViewById(R.id.register);
        emailid = (TextView)findViewById(R.id.emailid);
        contact = (TextView)findViewById(R.id.contact);
        income = (TextView)findViewById(R.id.income);
        membercount = (TextView)findViewById(R.id.membercount);
        oldage = (TextView)findViewById(R.id.oldage);
        daycare = (TextView)findViewById(R.id.daycare);
        address=(TextView)findViewById(R.id.address);
        requestforpublic = (TextView)findViewById(R.id.requesttopublic);
        accepted = (Button)findViewById(R.id.reqtopublic);
        govrequest = (TextView)findViewById(R.id.reqtogov);
        acceptbutforgov = (Button)findViewById(R.id.reqgov);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.idofviewpager);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userid = sharedPreferences.getString("userid","");
        menubutton = (ImageButton)findViewById(R.id.menubutton);

        DatabaseReference myRef = database.getReference("users/oldagehomeimage/"+userid);
        final DatabaseReference myRef2 = database.getReference("users/oldagehomes/"+userid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    arrayList.add(dataSnapshot1.getValue().toString());
                    Log.e(TAG,dataSnapshot1.getValue().toString());
                }
                viewPager.setAdapter(new PagerAdapterForViewPager(OldageHome.this,arrayList));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                description.setText(dataSnapshot.child("details").getValue().toString());
                title.setText(dataSnapshot.child("name").getValue().toString());
                register.setText("reg no: " + dataSnapshot.child("reg:no").getValue().toString());
                income.setText("income: " + dataSnapshot.child("income").getValue().toString());
                membercount.setText("member count: " + dataSnapshot.child("member count").getValue().toString());
                oldage.setText("oldage home available: " + dataSnapshot.child("oldage").getValue().toString());
                daycare.setText("daycare available: " + dataSnapshot.child("daycare").getValue().toString());
                contact.setText("contact: " + dataSnapshot.child("contact").getValue().toString());
                emailid.setText("email id: " + dataSnapshot.child("email").getValue().toString());
                address.setText("address: " + dataSnapshot.child("address").getValue().toString());
                requestforpublic.setText("Request: "+dataSnapshot.child("request").getValue().toString());
                govrequest.setText(dataSnapshot.child("govtrequest").getValue().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(OldageHome.this, v);

                MenuInflater inflater = popup.getMenuInflater();
                popup.setOnMenuItemClickListener(OldageHome.this);
                inflater.inflate(R.menu.menuf, popup.getMenu());
                popup.show();



            }
        });
        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef2.child("request").setValue(requestforpublic.getText().toString());

            }
        });

        acceptbutforgov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef2.child("govtrequest").setValue(govrequest.getText().toString());
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
                Intent intent = new Intent(OldageHome.this, MainActivity.class);
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
