package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class StudentGoITActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_go_i_t);

        //--------------Go to it admin recycle list-------------------------------------

        TextView txt_it = (TextView) findViewById(R.id.Sitid);
        txt_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FirebaseAuth.getInstance().signOut();
                Intent openRetriveDataRecycleView = new Intent(StudentGoITActivity.this, RetriveDataRecycleView.class);
                startActivity(openRetriveDataRecycleView);
            }
        });

        //--------------Go to electronic admin recycle list-----------------------------

        TextView txt_elc = (TextView) findViewById(R.id.Selectronicid);
        txt_elc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRetriveelectronicDataRecycleView = new Intent(StudentGoITActivity.this, RetriveelectronicDataRecycleView.class);
                startActivity(openRetriveelectronicDataRecycleView);
            }
        });

        //--------------Go to business admin recycle list-----------------------------

        TextView txt_bus = (TextView) findViewById(R.id.Sbusinessid);
        txt_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRetrivebusinessDataRecycleView = new Intent(StudentGoITActivity.this, RetrivebusinessDataRecycleView.class);
                startActivity(openRetrivebusinessDataRecycleView);
            }
        });

        //--------------Go to civil admin recycle list-----------------------------

        TextView txt_civil = (TextView) findViewById(R.id.Scivilid);
        txt_civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRetrivecivilDataRecycleView = new Intent(StudentGoITActivity.this, RetrivecivilDataRecycleView.class);
                startActivity(openRetrivecivilDataRecycleView);
            }
        });

        TextView txt_mecha = (TextView) findViewById(R.id.Smechanicalid);
        txt_mecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRetrivemechanicalDataRecycleView = new Intent(StudentGoITActivity.this, RetrivemechanicalDataRecycleView.class);
                startActivity(openRetrivemechanicalDataRecycleView);
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