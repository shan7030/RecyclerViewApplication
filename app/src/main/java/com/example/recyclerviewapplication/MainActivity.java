package com.example.recyclerviewapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.child("Not ok").setValue("TIME IS MONEY");

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        final ArrayList<Object> productList;

        productList = new ArrayList<>();

       final menuadapter adapter = new menuadapter(this, productList);
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Patients_info");

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    String date = uniqueKeySnapshot.getKey();

                    productList.add(new menuclass(""+date,date+""));
                    Log.v("MainActivity",""+date);

                    //
                    recyclerView.setAdapter(adapter);


                }
                //creating recyclerview adapter

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



        //setting adapter to recyclerview

        //adding some items to our list

    }
}
