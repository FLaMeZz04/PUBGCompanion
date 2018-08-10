package com.flamezz.pubgcompanion;

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

public class MiramarTab extends Fragment {

    private ArrayList<LootDescription> arrayList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.miramar_tab,container,false);
        final RecyclerView miramarRecyclerView = view.findViewById(R.id.miramarRecyclerView);
        miramarRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://pubg-companion-af4fc.firebaseio.com/Items/Loot Zones/Miramar/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    LootDescription loot = data.getValue(LootDescription.class);
                    arrayList.add(loot);
                }
                MiramarAdapter adapter = new MiramarAdapter(arrayList);
                miramarRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
