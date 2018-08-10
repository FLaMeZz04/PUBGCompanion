package com.flamezz.pubgcompanion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ErangelAdapter extends RecyclerView.Adapter<ErangelAdapter.ErangleViewHolder> {

    private ArrayList<LootDescription> arrayList;
    ErangelAdapter(ArrayList<LootDescription> arrayList)
    {
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ErangleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_lootzone_layout,parent,false);
        ErangleViewHolder viewHolder = new ErangleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ErangleViewHolder holder, int position) {
        holder.title_lootzone.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getImage()).into(holder.image_lootzone);
        holder.item1_lootzone.setText("Loot Quality:\t\t"+arrayList.get(position).getQuality());
        holder.item2_lootzone.setText("Loot Quantity:\t\t"+arrayList.get(position).getQuantity());
        holder.item3_lootzone.setText("Risk:\t\t"+arrayList.get(position).getRisk());
        holder.descrption_lootzone.setText(arrayList.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ErangleViewHolder extends RecyclerView.ViewHolder
    {
        TextView title_lootzone,item1_lootzone,item2_lootzone,item3_lootzone,descrption_lootzone;
        ImageView image_lootzone;
        ErangleViewHolder(View view)
        {
            super(view);
            title_lootzone = view.findViewById(R.id.title_lootzone);
            item1_lootzone = view.findViewById(R.id.item1_lootzone);
            item2_lootzone = view.findViewById(R.id.item2_lootzone);
            item3_lootzone = view.findViewById(R.id.item3_lootzone);
            descrption_lootzone = view.findViewById(R.id.descrption_lootzone);
            image_lootzone = view.findViewById(R.id.image_lootzone);

        }
    }
}
