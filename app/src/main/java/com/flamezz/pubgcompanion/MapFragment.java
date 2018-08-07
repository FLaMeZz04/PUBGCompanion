package com.flamezz.pubgcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private ArrayList<MapsClass> arrayList;
    private DatabaseReference databaseReference;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_map, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Maps");
        final RecyclerView mapRecyclerView = view.findViewById(R.id.mapRecyclerView);
        mapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    MapsClass maps = data.getValue(MapsClass.class);
                    arrayList.add(maps);
                }
                MapAdapter adapter = new MapAdapter(arrayList, new OnMapClick() {
                    @Override
                    public void onClick(String name, String image,String map) {
                        Intent intent = new Intent(getContext(),MapsActivity.class);
                        intent.putExtra("NAME",name);
                        intent.putExtra("IMAGE",image);
                        intent.putExtra("MAP",map);
                        startActivity(intent);
                    }
                });
                mapRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


}
