package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ElectronicAdapter extends RecyclerView.Adapter<ElectronicAdapter.ViewHolder> {

    Context context;
    List<PutPOST> putPOSTList;

    public ElectronicAdapter(Context context, List<PutPOST> putPOSTList){
        this.context = context;
        this.putPOSTList = putPOSTList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.desigin_electronicjob_recycle,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PutPOST putPOST = putPOSTList.get(position);
        holder.etvName.setText("Name="+ putPOST.name);
        holder.etvCaption.setText("Caption=" + putPOST.caption);

        String imageUri = null;
        imageUri = putPOST.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.imageView.getContext(), CompanyElectronic_Activity.class);
                intent.putExtra("eimage",putPOST.getImage());
                intent.putExtra("ecap",putPOST.getCaption());
                holder.imageView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return putPOSTList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView etvName, etvCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.etvimage_recycleview_id);
            etvName = itemView.findViewById(R.id.etvname_recycleview_id);
            etvCaption = itemView.findViewById(R.id.etvcaption_recycleview_id);
        }
    }
}


