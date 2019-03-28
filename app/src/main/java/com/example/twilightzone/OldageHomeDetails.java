package com.example.twilightzone;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twilightzone.adapters.PagerAdapterForViewPager;
import com.example.twilightzone.buissinessentities.OldageImage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wonderkiln.blurkit.BlurKit;

import java.util.ArrayList;

public class OldageHomeDetails extends AppCompatActivity {
    Button accepted,acceptbutforgov;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView title ,description,register,emailid,contact,daycare,oldage,membercount,income,address,requestforpublic,govrequest;
    final ArrayList<String> arrayList = new ArrayList<>();
private static final String TAG = "Policr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlurKit.init(this);
        setContentView(R.layout.activity_oldage_home_details);
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
        requestforpublic = (TextView)findViewById(R.id.requestforpublic);
        accepted = (Button)findViewById(R.id.acceptrequest);
        govrequest = (TextView)findViewById(R.id.govrequest);
        acceptbutforgov = (Button)findViewById(R.id.govaccept);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.idofviewpager);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        //BlurKit.getInstance().blur(viewPager, 12);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        final String person = sharedPreferences.getString("person","null");
        final String userid = getIntent().getStringExtra("userid");

        govrequest.setVisibility(View.INVISIBLE);
        acceptbutforgov.setVisibility(View.INVISIBLE);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("users/oldagehomeimage/"+userid);

        DatabaseReference myRef2 = database.getReference("users/oldagehomes/"+userid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                   arrayList.add(dataSnapshot1.getValue().toString());
                   Log.e(TAG,dataSnapshot1.getValue().toString());
               }
                viewPager.setAdapter(new PagerAdapterForViewPager(OldageHomeDetails.this,arrayList));
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
                        switch(person){


                            case "government":
                                govrequest.setVisibility(View.VISIBLE);
                                acceptbutforgov.setVisibility(View.VISIBLE);
                                govrequest.setText(dataSnapshot.child("govtrequest").getValue().toString());
                                break;
                            default:
                                govrequest.setVisibility(View.INVISIBLE);
                                acceptbutforgov.setVisibility(View.INVISIBLE);
                                break;

                        }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("name","0");
                String contact = sharedPreferences.getString("contact","0");
                DatabaseReference myRef3 = database.getReference("users/oldagehomes/"+userid);
                myRef3.child("request").setValue("accepted by: " + name +" and contact by "+ contact);
            }
        });
        acceptbutforgov.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name = sharedPreferences.getString("name","0");
        DatabaseReference myRef3 = database.getReference("users/oldagehomes/"+userid);
        myRef3.child("govtrequest").setValue("accepted by: government" );
    }
});


    }



}