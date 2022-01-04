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

public class CivilAdapter extends RecyclerView.Adapter<CivilAdapter.ViewHolder> {

    Context context;
    List<PutPOST> putPOSTList;

    public CivilAdapter(Context context, List<PutPOST> putPOSTList){
        this.context = context;
        this.putPOSTList = putPOSTList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.desigin_civiljob_recycle,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PutPOST putPOST = putPOSTList.get(position);
        holder.ctvName.setText("Name="+ putPOST.name);
        holder.ctvCaption.setText("Caption=" + putPOST.caption);

        String imageUri = null;
        imageUri = putPOST.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.imageView.getContext(), CompanyCivil_Activity.class);
                intent.putExtra("cimage",putPOST.getImage());
                intent.putExtra("ccap",putPOST.getCaption());
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
        TextView ctvName, ctvCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ctvimage_recycleview_id);
            ctvName = itemView.findViewById(R.id.ctvname_recycleview_id);
            ctvCaption = itemView.findViewById(R.id.ctvcaption_recycleview_id);
        }
    }
}



