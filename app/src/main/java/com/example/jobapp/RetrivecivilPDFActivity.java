package com.example.jobapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrivecivilPDFActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<putPDF> studentuploadedcivilPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrivecivil_p_d_f);

        listView = findViewById(R.id.CIVILgetallpdf);
        studentuploadedcivilPDF = new ArrayList<>();

        retrievePDFFileselec();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                putPDF putPDF = studentuploadedcivilPDF.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });
    }

    private void retrievePDFFileselec(){
        databaseReference = FirebaseDatabase.getInstance().getReference("uploadPDF").child("CIVIL");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //-------------------Retrieve all civil the pdf file on the firebase----------------------------

                for(DataSnapshot ds:snapshot.getChildren()){
                    putPDF putPDF = ds.getValue(com.example.jobapp.putPDF.class);
                    studentuploadedcivilPDF.add(putPDF);
                }

                String[] uploadedName = new String[studentuploadedcivilPDF.size()];

                for (int i=0; i<uploadedName.length; i++){
                    uploadedName[i] = studentuploadedcivilPDF.get(i).getName();
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploadedName){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);

                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(20);
                        return view;
                    }
                };

                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}