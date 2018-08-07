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

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder> {

    private ArrayList<Guns> arrayList;
    WeaponAdapter(ArrayList<Guns> arrayList)
    {
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public WeaponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_new,parent,false);
        WeaponViewHolder viewHolder = new WeaponViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponViewHolder holder, int position) {
        holder.name_text.setText((arrayList.get(position).getName()));
        Picasso.get().load((arrayList.get(position).getImage())).into(holder.name_weapon);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class WeaponViewHolder extends RecyclerView.ViewHolder
    {
        TextView name_text;
        ImageView name_weapon;
        WeaponViewHolder(View view)
        {
            super(view);
            name_weapon = view.findViewById(R.id.name_weapon);
            name_text = view.findViewById(R.id.name_text);
        }
    }

}
