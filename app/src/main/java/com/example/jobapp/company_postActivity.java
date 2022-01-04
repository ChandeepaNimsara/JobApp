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

public class company_postActivity extends AppCompatActivity {

    ImageView img;
    TextView txt;

    String i,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_post);

        Button btn_cmnt = (Button) findViewById(R.id.apply1);  //get comment btn


//--------------------------get it post and caption--------------------------
        img = findViewById(R.id.it_post1);
        txt = findViewById(R.id.cguit1);

        i = getIntent().getExtras().getString("image");
        t = getIntent().getExtras().getString("cap1");
        Picasso.get().load(i).into(img);
        txt.setText(t);

//------------------------------------------------------------------------

        btn_cmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FirebaseAuth.getInstance().signOut();
                Intent openApplyFirstITJobActivity = new Intent(company_postActivity.this, ApplyFirstITJobActivity.class);
                startActivity(openApplyFirstITJobActivity);
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