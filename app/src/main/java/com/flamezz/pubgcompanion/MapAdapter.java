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

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {

    ArrayList<MapsClass> arrayList;
    OnMapClick onMapClick;
    MapAdapter(ArrayList<MapsClass> arrayList,OnMapClick onMapClick)
    {
        this.arrayList=arrayList;
        this.onMapClick=onMapClick;
    }
    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_layout_recyclerview,parent,false);
        MapViewHolder holder = new MapViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, final int position) {
        MapsClass maps = arrayList.get(position);
        holder.mapName.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getImage()).into(holder.mapImage);
        Picasso.get().load(arrayList.get(position).getMap());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMapClick.onClick(arrayList.get(position).getName(),arrayList.get(position).getImage(),arrayList.get(position).getMap());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MapViewHolder extends RecyclerView.ViewHolder
    {
        TextView mapName;
        ImageView mapImage;
        MapViewHolder(View view)
        {
            super(view);
            mapName = view.findViewById(R.id.mapName);
            mapImage = view.findViewById(R.id.map_image);
        }
    }
}
