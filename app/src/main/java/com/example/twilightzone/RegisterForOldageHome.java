package com.example.twilightzone;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.twilightzone.buissinessentities.OldRegisEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterForOldageHome extends AppCompatActivity {
/*
* user id
* password
* address
* contact no
* email
*  availability of daycare
* member count
* name of oldage home
* register no
* income
*image
*details
*
*
* */

String userid,password,address,contact,email,daycare,membercount,name,registerno,income,image,details,description,oldagehome;
EditText euserid,epassword,eaddress,econtact,eemail,emembercount,ename,eregisterno,eincome,edetails,edescription;
Button submit;
    ToggleButton eoldagehome,edaycare;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/oldagehomes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_oldage_home);
                euserid = (EditText)findViewById(R.id.userid);
                epassword = (EditText)findViewById(R.id.password);
                eaddress = (EditText)findViewById(R.id.address);
                econtact = (EditText)findViewById(R.id.contact);
                eemail = (EditText)findViewById(R.id.email);
        final ToggleButton eoldagehome = (ToggleButton) findViewById(R.id.daycare);
        eoldagehome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    daycare = "yes";
                } else {
                    daycare = "no";
                }
            }
        });

        final ToggleButton edaycare = (ToggleButton) findViewById(R.id.oldagehome);
        edaycare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    oldagehome = "yes";
                } else {
                    oldagehome = "no";

                }
            }
        });
                emembercount = (EditText)findViewById(R.id.membercount);
                ename = (EditText)findViewById(R.id.name);
                eregisterno = (EditText)findViewById(R.id.registerno);
                eincome = (EditText)findViewById(R.id.income);
                edetails = (EditText)findViewById(R.id.details);
                edescription = (EditText)findViewById(R.id.desc);

              submit = (Button)findViewById(R.id.submit_oldage);


        submit.setOnClickListener(new View.OnClickListener() {
        @Override
       public void onClick(View v) {
          userid = euserid.getText().toString();
          password = epassword.getText().toString();
          name   = ename.getText().toString();
          membercount = emembercount.getText().toString();
          details = edetails.getText().toString();
          description = edescription.getText().toString();
          address = eaddress.getText().toString();
          income = eincome.getText().toString();
          if( edaycare.getText().toString().equals("ON")){
              daycare = "yes";

          }
          else {
              daycare = "no";
          }
          if( eoldagehome.getText().toString().equals("ON")){
              oldagehome = "yes";
          }else {
              oldagehome = "no";
          }
          contact = econtact.getText().toString();
         email  = eemail.getText().toString();
          registerno = eregisterno.getText().toString();

     checkdatabase(userid,password,address,contact,email,daycare,membercount,name,registerno,income,details,description,oldagehome);



    }
});
    }



    public void checkdatabase(final String userid, final String password, final String address, final String contact, final String email, final String daycare, final String membercount, final String name, final String registerno, final String income, final String details, final String description, final String oldagehome ){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userid)){
                    Toast.makeText(RegisterForOldageHome.this,"You cant create your account using this userid",Toast.LENGTH_LONG).show();
                }else{
                    OldRegisEntity oldRegisEntity = new OldRegisEntity(name,address,membercount,income,registerno,oldagehome,daycare,password,contact,email,description,details);
                    myRef.child(userid).setValue(oldRegisEntity);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
