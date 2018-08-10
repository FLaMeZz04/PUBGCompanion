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

public class MiramarAdapter extends RecyclerView.Adapter<MiramarAdapter.MiramarViewHolder> {

    private ArrayList<LootDescription> arrayList;
    MiramarAdapter(ArrayList<LootDescription> arrayList)
    {
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public MiramarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_miramar_layout,parent,false);
        MiramarViewHolder viewHolder = new MiramarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiramarViewHolder holder, int position) {
        holder.miramar_title.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getImage()).into(holder.miramar_image);
        holder.miramar_item1.setText("Loot Quality:\t"+arrayList.get(position).getQuality());
        holder.miramar_item2.setText("Loot Quantity:\t"+arrayList.get(position).getQuantity());
        holder.miramar_item3.setText("Risk:\t"+arrayList.get(position).getRisk());
        holder.miramar_description.setText(arrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MiramarViewHolder extends RecyclerView.ViewHolder
    {
        ImageView miramar_image;
        TextView miramar_item1,miramar_item2,miramar_item3,miramar_description,miramar_title;
        MiramarViewHolder(View view)
        {
            super(view);
            miramar_image = view.findViewById(R.id.miramar_image);
            miramar_item1 = view.findViewById(R.id.miramar_item1);
            miramar_item2 = view.findViewById(R.id.miramar_item2);
            miramar_item3 = view.findViewById(R.id.miramar_item3);
            miramar_description = view.findViewById(R.id.miramar_description);
            miramar_title = view.findViewById(R.id.miramar_title);
        }
    }
}
