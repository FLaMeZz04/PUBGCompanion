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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<News> arrayList;
    NewsAdapter(ArrayList<News> arrayList)
    {
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_recyclerview,parent,false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
       holder.news_desp.setText(arrayList.get(position).getDescription());
       holder.news_header.setText(arrayList.get(position).getHeading());
       Picasso.get().load(arrayList.get(position).getImage()).into(holder.news_image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView news_image;
        TextView news_header,news_desp;
        NewsViewHolder(View view)
        {
            super(view);
            news_image = view.findViewById(R.id.news_image);
            news_header = view.findViewById(R.id.news_header);
            news_desp = view.findViewById(R.id.news_desp);
        }
    }
}
