package com.flamezz.pubgcompanion;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
public class HomeFragment extends Fragment {

    private ArrayList<String> arrayList;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);
        final ViewPager viewPager = view.findViewById(R.id.viewPager);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    String images = data.getValue(String.class);
                    arrayList.add(images);
                }
                CustomPager customPager = new CustomPager(getContext(),arrayList);
                viewPager.setAdapter(customPager);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                        int splash_time = 6000;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                viewPager.setCurrentItem(position+1);
                                if(viewPager.getCurrentItem()==9)
                                {
                                    viewPager.setCurrentItem(0);
                                }
                                }
                            },splash_time);
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
