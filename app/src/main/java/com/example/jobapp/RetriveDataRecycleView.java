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

public class RetriveDataRecycleView extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;

    RecyclerView recyclerView;
    ITAdapter itAdapter;
    List<PutPOST> putPTList;

    ImageView imageView1;
    TextView txtCap1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data_recycle_view);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("IT field");
        mStorage = FirebaseStorage.getInstance();

        recyclerView = findViewById(R.id.recycleviewIT_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       putPTList = new ArrayList<PutPOST>();
       itAdapter = new ITAdapter(RetriveDataRecycleView.this,putPTList);
       recyclerView.setAdapter(itAdapter);

        imageView1 = findViewById(R.id.tvimage_recycleview_id);
        txtCap1 = findViewById(R.id.tvcaption_recycleview_id);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                PutPOST putPOST = dataSnapshot.getValue(PutPOST.class);
                putPTList.add(putPOST);
                itAdapter.notifyDataSetChanged();
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