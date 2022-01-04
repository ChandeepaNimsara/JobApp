package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");

        Button lgn = (Button) findViewById(R.id.loginbtn);
        EditText text_usernamelogin = (EditText) findViewById(R.id.username_log);    //get username
        EditText text_pwlogin = (EditText) findViewById(R.id.password_login);
        ProgressBar progressBar = findViewById(R.id.progressBarLogin);
        TextView registerTxtBtn = findViewById(R.id.registerTxtbtn);

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = text_usernamelogin.getText().toString().trim();
                String password = text_pwlogin.getText().toString().trim();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter your details for login",Toast.LENGTH_LONG).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(),"Please enter valid email",Toast.LENGTH_LONG).show();
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                    databaseReference.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if(snapshot.exists()){
                                                for(DataSnapshot d:snapshot.getChildren()){
                                                    String role = Objects.requireNonNull(d.child("role").getValue()).toString();
                                                    String uemail = Objects.requireNonNull(d.child("email").getValue()).toString();

                                                    if(role.equals("admin") && email==uemail){
                                                        startActivity(new Intent(loginActivity.this, addpostActivity.class));
                                                    }
                                                    else if(role.equals("student") && email==uemail){
                                                        startActivity(new Intent(loginActivity.this, StudentGoITActivity.class));
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        Log.d("loginActivity", "signInWithEmail:success");

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("loginActivity", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(loginActivity.this, "Authentication failed. Try Again!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });


        registerTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opennextActivity = new Intent(loginActivity.this, registerActivity.class);
                startActivity(opennextActivity);
            }
        });

    }
}