package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class registerActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");

        Button reg = (Button) findViewById(R.id.registerbutton);
        EditText text_fullregister = (EditText) findViewById(R.id.fullname_reg);
        EditText text_regid = (EditText) findViewById(R.id.id_reg);
        EditText text_emailregister = (EditText) findViewById(R.id.email_reg);
        EditText text_pwregister = (EditText) findViewById(R.id.password_reg);
        EditText text_cnfpsw = (EditText) findViewById(R.id.confirm_password_reg);
        TextView loginTxtBtn = findViewById(R.id.loginTxtbtn);
        ProgressBar progressBar = findViewById(R.id.progressReg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = text_emailregister.getText().toString().trim();
                String fullName = text_fullregister.getText().toString().trim();
                String regId = text_regid.getText().toString().trim();
                String password = text_pwregister.getText().toString().trim();
                String confirmPassword = text_cnfpsw.getText().toString().trim();

                if(fullName.equals("") || regId.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Required fields are missing!",Toast.LENGTH_LONG).show();
                }
                else if(!password.equals(confirmPassword)){
                    Toast.makeText(getApplicationContext(),"Confirm password mismatched!",Toast.LENGTH_LONG).show();
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, confirmPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("registerActivity", "createUserWithEmail:success");
                                        User user = new User(fullName, regId, "student", email);

                                        ref.child(mAuth.getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(registerActivity.this, "User has been registered successfully!",
                                                            Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                    finish();

                                                }else{
                                                    Toast.makeText(registerActivity.this, "Failed to register! Try again!",
                                                            Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });

                                    } else{
                                        Toast.makeText(registerActivity.this, "Failed to register! Try again!",
                                                Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                            }});
                }
            }
        });

        loginTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opennextActivity = new Intent(registerActivity.this, loginActivity.class);
                startActivity(opennextActivity);
            }
        });

    }
}