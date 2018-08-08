package com.flamezz.pubgcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubItemsAdapter extends RecyclerView.Adapter<SubItemsAdapter.RecyclerViewHolder> {

   private ArrayList<Items> arrayList;
   private Context context;
   private onClcikListener listener;
    SubItemsAdapter(ArrayList<Items> arrayList,onClcikListener listener)
    {
        this.arrayList=arrayList;
        this.listener=listener;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_items_layout,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.titleName.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getImage()).into(holder.itemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(arrayList.get(position).getName());


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleName;
        ImageView itemImage;
        RecyclerViewHolder(View view)
        {
            super(view);
            titleName = view.findViewById(R.id.itemName);
            itemImage = view.findViewById(R.id.itemImage);
        }
    }
}
