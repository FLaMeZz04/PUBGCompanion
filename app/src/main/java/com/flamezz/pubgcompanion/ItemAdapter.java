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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RecyclerViewHolder> {

    ArrayList<Items> arrayList;
    Context context;

    ItemAdapter(ArrayList<Items> arrayList)
    {
        this.arrayList=arrayList;
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
            Items item = arrayList.get(position);
            holder.titleName.setText(arrayList.get(position).getName());

            Picasso.get().load(arrayList.get(position).getImage()).into(holder.imageName);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,SubItemsActivity.class);
                    intent.putExtra("NAME",arrayList.get(position).getName());
                    context.startActivity(intent);
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
        ImageView imageName;
        RecyclerViewHolder(View view)
        {
            super(view);
            titleName = view.findViewById(R.id.itemName);
            imageName = view.findViewById(R.id.itemImage);


        }
    }
}
