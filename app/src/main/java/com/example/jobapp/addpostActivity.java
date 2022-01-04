package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class addpostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);


        //-----------------IT admin-------------------------------

        TextView txt_it = (TextView) findViewById(R.id.itid);
        txt_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //FirebaseAuth.getInstance().signOut();
               Intent openAdminUseForAddPostActivity = new Intent(addpostActivity.this, AdminUseForAddPostActivity.class);
               startActivity(openAdminUseForAddPostActivity);
            }
        });

        //-----------------Electronic admin------------------------

        TextView txt_electronic = (TextView) findViewById(R.id.electronicid);
        txt_electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAdminUseForelectronicAddPostActivity = new Intent(addpostActivity.this, AdminUseForelectronicAddPostActivity.class);
                startActivity(openAdminUseForelectronicAddPostActivity);
            }
        });


        //-----------------Business admin------------------------

        TextView txt_business = (TextView) findViewById(R.id.businessid);
        txt_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAdminUseForbusinessAddPostActivity = new Intent(addpostActivity.this, AdminUseForbusinessAddPostActivity.class);
                startActivity(openAdminUseForbusinessAddPostActivity);
            }
        });

        //-----------------Civil admin------------------------

        TextView txt_cvil = (TextView) findViewById(R.id.civilid);
        txt_cvil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAdminUseForcivilAddPostActivity = new Intent(addpostActivity.this, AdminUseForcivilAddPostActivity.class);
                startActivity(openAdminUseForcivilAddPostActivity);
            }
        });

        //-----------------Mechanical admin------------------------

        TextView txt_mechanical = (TextView) findViewById(R.id.mechanicalid);
        txt_mechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAdminUseForAddPostActivity = new Intent(addpostActivity.this, AdminUseFormechanicalAddPostActivity.class);
                startActivity(openAdminUseForAddPostActivity);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.joballpdfmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.app1){
            Intent itpdf = new Intent(addpostActivity.this, RetriveitPDFActivity.class);
            startActivity(itpdf);
        }

        if(id==R.id.app2){
            Intent epdf = new Intent(addpostActivity.this, RetriveelectronicPDFActivity.class);
            startActivity(epdf);
        }

        if(id==R.id.app3){
            Intent bpdf = new Intent(addpostActivity.this, RetrivebusinessPDFActivity.class);
            startActivity(bpdf);
        }

        if(id==R.id.app4){
            Intent cpdf = new Intent(addpostActivity.this, RetrivecivilPDFActivity.class);
            startActivity(cpdf);
        }

        if(id==R.id.app5){
            Intent mpdf = new Intent(addpostActivity.this, RetrivemechanicalPDFActivity.class);
            startActivity(mpdf);
        }

        return super.onOptionsItemSelected(item);
    }
}