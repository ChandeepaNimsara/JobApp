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

public class CompanyMechanical extends AppCompatActivity {

    ImageView mimg;
    TextView mtxt;

    String mi,mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_mechanical);

        Button bbtn_cmnt = (Button) findViewById(R.id.mapply1);  //get comment btn


//--------------------------Get mechanical post and caption--------------------------
        mimg = findViewById(R.id.m_post);
        mtxt = findViewById(R.id.mcgu);

        mi = getIntent().getExtras().getString("mimage");
        mt = getIntent().getExtras().getString("mcap");
        Picasso.get().load(mi).into(mimg);
        mtxt.setText(mt);

//--------------------------------------------------------------------------------

        bbtn_cmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FirebaseAuth.getInstance().signOut();
                Intent openApplyFirstBUSINESSJobActivity = new Intent(CompanyMechanical.this, ApplyFirstMECHANICALJobActivity.class);
                startActivity(openApplyFirstBUSINESSJobActivity);
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