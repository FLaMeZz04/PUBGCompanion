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
public class ItemFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Items> arrayList;
    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot details:dataSnapshot.getChildren())
                {
                     Items items = details.getValue(Items.class);
                    arrayList.add(items);
                }
                ItemAdapter adapter = new ItemAdapter(arrayList, new onClcikListener() {
                    @Override
                    public void onClick(String name) {
                        switch (name)
                        {
                            case "Loot Zones":
                            {
                                Intent intent = new Intent(getContext(),LootZoneActivity.class);
                                startActivity(intent);
                                break;
                            }
                            default:
                            {
                                Intent intent = new Intent(getContext(),SubItemsActivity.class);
                                intent.putExtra("NAME",name);
                                startActivity(intent);
                                break;
                            }
                        }

                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }


}
