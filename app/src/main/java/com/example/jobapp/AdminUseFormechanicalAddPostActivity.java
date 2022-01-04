package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminUseFormechanicalAddPostActivity extends AppCompatActivity {


    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    EditText adminm1,admin_main_mpost_caption;
    ImageButton mnotificationAdminpic;
    Button muploadAdminPost;
    private static final int Gallery_code = 2;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_use_formechanical_add_post);


        mnotificationAdminpic = findViewById(R.id.mnotificationAdminpic);
        adminm1 = findViewById(R.id.adminm1);
        admin_main_mpost_caption = findViewById(R.id.admin_main_mpost_caption);
        muploadAdminPost = findViewById(R.id.muploadAdminPost);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Mechanical field");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);


        mnotificationAdminpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_code && resultCode==RESULT_OK){
            imageUrl = data.getData();
            mnotificationAdminpic.setImageURI(imageUrl);
        }

        muploadAdminPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = adminm1.getText().toString().trim();
                String In = admin_main_mpost_caption.getText().toString().trim();

                if (!(fn.isEmpty() && In.isEmpty() && imageUrl!=null)){
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference path = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    path.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("name").setValue(fn);
                                    newPost.child("caption").setValue(In);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    Toast.makeText(AdminUseFormechanicalAddPostActivity.this, "Job vacancy post is successfully Uploaded", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }
                            });

                        }
                    });
                }

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
