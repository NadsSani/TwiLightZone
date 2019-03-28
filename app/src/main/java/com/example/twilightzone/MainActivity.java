package com.example.twilightzone;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {
Button login,signup;
EditText userid,password;
private final static String TAG = "NAdeem";
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.loginbutton);
        signup = (Button)findViewById(R.id.signupbutton);
        userid = (EditText)findViewById(R.id.userid);
        password = (EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = userid.getText().toString();

                final String pass = password.getText().toString();

signin(user,pass);


                }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View mView = View.inflate(MainActivity.this,R.layout.page_for_registerform_selection,null);
                /*editText = (EditText)mView.findViewById(R.id.editText36);*/
                builder.setView(mView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                final Button signupforoldage = (Button) mView.findViewById(R.id.signupoldagehome);
                final Button signupfor = (Button) mView.findViewById(R.id.signuppublic);
                final Button signupforsocial = (Button)mView.findViewById(R.id.signupsocial);

                signupforoldage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent1 = new Intent(MainActivity.this,RegisterForOldageHome.class);
                        startActivity(intent1);
alertDialog.cancel();

                    }
                });
                signupfor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent2 = new Intent(MainActivity.this,RegisterForPublic.class);
                        startActivity(intent2);
alertDialog.cancel();
                    }
                });
                signupforsocial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent3 = new Intent(MainActivity.this,RegisterForSocialGroup.class);
                    startActivity(intent3);

                    }
                });

            }
        });
    }
    public void sharedToSave( DataSnapshot dataSnapshot2){
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name",dataSnapshot2.child("name").getValue().toString());
        editor.putString("address",dataSnapshot2.child("address").getValue().toString());
        editor.putString("daycare",dataSnapshot2.child("daycare").getValue().toString());
        editor.putString("desc",dataSnapshot2.child("desc").getValue().toString());
        editor.putString("details",dataSnapshot2.child("details").getValue().toString());
        editor.putString("email",dataSnapshot2.child("email").getValue().toString());
        editor.putString("income",dataSnapshot2.child("income").getValue().toString());
        editor.putString("membercount",dataSnapshot2.child("member count").getValue().toString());
        editor.putString("contact",dataSnapshot2.child("contact").getValue().toString());
        editor.putString("oldage",dataSnapshot2.child("oldage").getValue().toString());
        editor.putString("regno",dataSnapshot2.child("reg:no").getValue().toString());
        editor.putString("request",dataSnapshot2.child("request").getValue().toString());
        editor.apply();
    }
    public void sharedToSavepub( DataSnapshot dataSnapshot2){
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name",dataSnapshot2.child("name").getValue().toString());
        editor.putString("address",dataSnapshot2.child("address").getValue().toString());
        editor.putString("contact",dataSnapshot2.child("contact").getValue().toString());
        editor.putString("email",dataSnapshot2.child("email").getValue().toString());
        editor.apply();
    }

    public void signin(final String user , final String pass){

        if(user.isEmpty() || pass.isEmpty()){
            Toast.makeText(MainActivity.this,"userid and password cannot be empty",Toast.LENGTH_LONG).show();
            return;
        }  Log.e(TAG,"mere liye outside ");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    for(DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()) {
                        // Log.e(TAG,dataSnapshot1.getChildren().toString());

                        if (user.equals(dataSnapshot2.getKey()) && pass.equals(dataSnapshot2.child("password").getValue())) {
                            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("userid",dataSnapshot2.getKey().toString());
                            editor.putString("password",dataSnapshot2.child("password").getValue().toString());
                            editor.putString("person",dataSnapshot1.getKey().toString());
                            editor.apply();

                            Log.e(TAG,"Got inside login");
                            switch (dataSnapshot1.getKey().toString()){

                                case "government":
                                    Log.e(TAG,"Got in gov");
                                    Intent intent = new Intent(MainActivity.this,Government.class);
                                    startActivity(intent);

                                    break;
                                case "admin":
                                    Intent intent1 = new Intent(MainActivity.this,Admin.class);
                                    startActivity(intent1);
                                    Log.e(TAG,"Got in admin");
                                    break;
                                case "socialgroups":
                                    Intent intent2 = new Intent(MainActivity.this,SocialGroups.class);
                                    startActivity(intent2);
                                    Log.e(TAG,"Got in social groupos");
                                    sharedToSavepub(dataSnapshot2);
                                    break;
                                case "public":
                                    Intent intent3 = new Intent(MainActivity.this,Public.class);
                                    startActivity(intent3);
                                    Log.e(TAG,"Got in public");
                                    sharedToSavepub(dataSnapshot2);
                                    break;
                                case "oldagehomes":
                                    Intent intent4 = new Intent(MainActivity.this,OldageHome.class);
                                    startActivity(intent4);
                                    Log.e(TAG,"Got ins oldage homes");
                                    sharedToSave(dataSnapshot2);
                                    break;
                            }
                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG,databaseError.toString());

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userids = sharedPreferences.getString("userid","");
        String pasws =  sharedPreferences.getString("password","");
        if(!userids.equals("") && !pasws.equals("")){
        signin(userids,pasws);
        }

    }
}
