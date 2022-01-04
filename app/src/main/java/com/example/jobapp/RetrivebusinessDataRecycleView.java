package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RetrivebusinessDataRecycleView extends AppCompatActivity {

    FirebaseDatabase bmDatabase;
    DatabaseReference bmRef;
    FirebaseStorage bStorage;

    RecyclerView brecyclerView;
    BusinessAdapter businessAdapter;
    List<PutPOST> putPTList;

    ImageView imageView3;
    TextView txtCap3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrivebusiness_data_recycle_view);

        bmDatabase = FirebaseDatabase.getInstance();
        bmRef = bmDatabase.getReference().child("Business field");
        bStorage = FirebaseStorage.getInstance();

        brecyclerView = findViewById(R.id.recycleviewBusiness_id);
        brecyclerView.setHasFixedSize(true);
        brecyclerView.setLayoutManager(new LinearLayoutManager(this));

        putPTList = new ArrayList<PutPOST>();
        businessAdapter = new BusinessAdapter(RetrivebusinessDataRecycleView.this,putPTList);
        brecyclerView.setAdapter(businessAdapter);

        imageView3 = findViewById(R.id.btvimage_recycleview_id);
        txtCap3 = findViewById(R.id.btvcaption_recycleview_id);


        bmRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                PutPOST putPOST = dataSnapshot.getValue(PutPOST.class);
                putPTList.add(putPOST);
                businessAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forlogout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idadmin = item.getItemId();

        if(idadmin==R.id.addminout){
            FirebaseAuth.getInstance().signOut();
        }
        return super.onOptionsItemSelected(item);
    }

}