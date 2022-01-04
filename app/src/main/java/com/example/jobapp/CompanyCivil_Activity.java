package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class CompanyCivil_Activity extends AppCompatActivity {

    ImageView cimg;
    TextView ctxt;

    String ci,ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_civil_);

        Button cbtn_cmnt = (Button) findViewById(R.id.capply1);  //get comment btn


//--------------------------Get civil post and caption--------------------------
        cimg = findViewById(R.id.clec_post);
        ctxt = findViewById(R.id.ccgu);

        ci = getIntent().getExtras().getString("cimage");
        ct = getIntent().getExtras().getString("ccap");
        Picasso.get().load(ci).into(cimg);
        ctxt.setText(ct);

//------------------------------------------------------------------------

        cbtn_cmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FirebaseAuth.getInstance().signOut();
                Intent openApplyFirstCIVILJobActivity = new Intent(CompanyCivil_Activity.this, ApplyFirstCIVILJobActivity.class);
                startActivity(openApplyFirstCIVILJobActivity);
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