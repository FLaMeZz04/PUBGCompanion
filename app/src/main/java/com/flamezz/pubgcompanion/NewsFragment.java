package com.flamezz.pubgcompanion;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private ImageView back_pressed;
    private DatabaseReference databaseReference;
    private ArrayList<News> arrayList;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news, container, false);
         final RecyclerView newsRecyclerView = view.findViewById(R.id.newsRecyclerView);
         newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         databaseReference = FirebaseDatabase.getInstance().getReference("News");
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arrayList = new ArrayList<>();
                 for(DataSnapshot snap:dataSnapshot.getChildren()) {
                     News news = snap.getValue(News.class);
                     arrayList.add(news);
                 }
                 NewsAdapter adapter = new NewsAdapter(arrayList);
                 newsRecyclerView.setAdapter(adapter);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

        return view;
    }



}
