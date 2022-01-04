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

public class CompanyElectronic_Activity extends AppCompatActivity {

    ImageView eimg;
    TextView etxt;

    String ei,et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_electronic_);

        Button ebtn_cmnt = (Button) findViewById(R.id.eapply1);  //get comment btn


//--------------------------Get electronic post and caption--------------------------
        eimg = findViewById(R.id.elec_post);
        etxt = findViewById(R.id.ecgu);

        ei = getIntent().getExtras().getString("eimage");
        et = getIntent().getExtras().getString("ecap");
        Picasso.get().load(ei).into(eimg);
        etxt.setText(et);

//------------------------------------------------------------------------

        ebtn_cmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FirebaseAuth.getInstance().signOut();
                Intent openApplyFirstELECTRONICJobActivity = new Intent(CompanyElectronic_Activity.this, ApplyFirstELECTRONICJobActivity.class);
                startActivity(openApplyFirstELECTRONICJobActivity);
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