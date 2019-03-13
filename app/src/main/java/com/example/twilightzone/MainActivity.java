package com.example.twilightzone;
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
                                     String val = dataSnapshot1.getChildren().toString();
                                     if (user.equals(dataSnapshot2.getKey()) && pass.equals(dataSnapshot2.child("password").getValue())) {

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
                                                 break;
                                             case "public":
                                                 Intent intent3 = new Intent(MainActivity.this,Public.class);
                                                 startActivity(intent3);
                                                 Log.e(TAG,"Got in public");
                                                 break;
                                             case "oldagehomes":
                                                 Intent intent4 = new Intent(MainActivity.this,OldageHome.class);
                                                 startActivity(intent4);
                                                 Log.e(TAG,"Got ins oldage homes");
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

        });
    }
}
