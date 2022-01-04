package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference ref;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("EXIT");
        dialog.setMessage("Do you want to exit from the app?");
        dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
                System.exit(0);
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        firebaseAuth.addAuthStateListener(authStateListener);



    }

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {

                ref.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        if (user != null) {
                            String role = user.role;
                            if(role.equals("student")){
                                Intent intent = new Intent(MainActivity.this, StudentGoITActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(role.equals("admin")){
                                Intent intent = new Intent(MainActivity.this, addpostActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }else {
                            Intent intent = new Intent(MainActivity.this, loginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NotNull DatabaseError error) {
                        // Failed to read value
                        Log.w("MainActivity", "Failed to read value.", error.toException());
                    }
                });
            }else {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}