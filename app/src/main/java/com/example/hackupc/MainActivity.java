package com.example.hackupc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button submit;
    static String appointmentID;
    View view;
    NameofPlace nameofPlace;
    FirebaseDatabase database;
    DatabaseReference reference;

    static int i = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        name = findViewById(R.id.name);
        submit = findViewById(R.id.submitButton);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("NameofPlace"); //references to the Name of place table on Firebase.

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.colorBlue);


    }


    /**
     * This method retrieves data from the Appointment table from the database and
     * checks in the appointment ID entered is equal to the appointment ID in the database.
     *
     * @param v
     */
    public void btnLogin_Click(View v) {



        final Map<String, NameofPlace> placeMap = new HashMap<>();

        final String apID = name.getText().toString();
        appointmentID = apID;

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean complete = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String a = ds.getKey();

                    nameofPlace = ds.getValue(NameofPlace.class);

                    //checks if its equal.
                    if (apID.equals("Place1")) {
                        complete = true;
                        Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if (!complete) {
                    Toast.makeText(MainActivity.this, "Creating new Chat", Toast.LENGTH_SHORT).show();
                    placeMap.put("Name", new NameofPlace(i,apID));
                    reference.setValue(placeMap);
                    i++;
                    Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }



        });
    }
}