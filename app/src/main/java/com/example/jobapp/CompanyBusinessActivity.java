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

public class CompanyBusinessActivity extends AppCompatActivity {

    ImageView bimg;
    TextView btxt;

    String bi,bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_business);

        Button bbtn_cmnt = (Button) findViewById(R.id.bapply1);  //get comment btn


//--------------------------Get business post and caption--------------------------
        bimg = findViewById(R.id.bus_post);
        btxt = findViewById(R.id.bcgu);

        bi = getIntent().getExtras().getString("bimage");
        bt = getIntent().getExtras().getString("bcap");
        Picasso.get().load(bi).into(bimg);
        btxt.setText(bt);

//--------------------------------------------------------------------------------

        bbtn_cmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FirebaseAuth.getInstance().signOut();
                Intent openApplyFirstBUSINESSJobActivity = new Intent(CompanyBusinessActivity.this, ApplyFirstBUSINESSJobActivity.class);
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