package com.flamezz.pubgcompanion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewItemDescriptionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Guns> arrayList;
    private String name,rootName;
    private TextView textview_header;
    private ImageView onGoBack;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_description);
        InitializeControls();
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
         "https://pubg-companion-af4fc.firebaseio.com/Items/"+rootName+"/Subitems/"+name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot snap:dataSnapshot.getChildren())
                {
                    String name = snap.child("name").getValue(String.class);
                    String image = snap.child("image").getValue(String.class);
                    Guns guns = new Guns(name,image);
                    arrayList.add(guns);
                }
                WeaponAdapter adapter = new WeaponAdapter(arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    private void InitializeControls()
    {

        name = getIntent().getStringExtra("name");
        rootName = getIntent().getStringExtra("parentname");
        textview_header = findViewById(R.id.textview_header);
        textview_header.setText(name);
        onGoBack = findViewById(R.id.onGoBack);
        onGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
