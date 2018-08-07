package com.flamezz.pubgcompanion;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {

    private ListView listView;
    private ArrayList<Trcks> arrayList;
    public TipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tips, container, false);
        listView = view.findViewById(R.id.myListView);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tricks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot snap:dataSnapshot.getChildren())
                {
                    Trcks trcks = snap.getValue(Trcks.class);
                    arrayList.add(trcks);
                }

                CustomAdapter customAdapter = new CustomAdapter(arrayList);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity().getApplication(), TipDescriptionActivity.class);
                    intent.putExtra("name", arrayList.get(i).getName());
                    intent.putExtra("image", arrayList.get(i).getImage());
                    intent.putExtra("description", arrayList.get(i).getDescription());
                    startActivity(intent);
                }
            });

        return view;
    }

    class  CustomAdapter extends BaseAdapter
    {
        ArrayList<Trcks> arrayList;
        CustomAdapter(ArrayList<Trcks> arrayList)
        {
            this.arrayList=arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.list_view_layout,null);
            TextView tricks_text = view.findViewById(R.id.tricks_text);
            tricks_text.setText(arrayList.get(i).getName());
            return view;
        }
    }

}
