package com.flamezz.pubgcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubItemsActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ArrayList<Items> arrayList;
    private ImageView backarrow;
    private Double capacity_new;
    private String parentName;
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_sub_items);
         parentName = getIntent().getStringExtra("NAME");
         recyclerView = findViewById(R.id.myRecyclerView);
         backarrow = findViewById(R.id.backArrow);
         backarrow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onBackPressed();
             }
         });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pubg-companion-af4fc.firebaseio.com/Items/"+parentName+"/Subitems");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot details:dataSnapshot.getChildren())
                {
                    String getname = details.child("name").getValue(String.class);
                    String getimage = details.child("image").getValue(String.class);
                    Items items = new Items(getname,getimage);
                    arrayList.add(items);
                }
                SubItemsAdapter adapter = new SubItemsAdapter(arrayList, new onClcikListener() {
                    @Override
                    public void onClick(String name) {
                        if (parentName.equals("Bow & Guns") || parentName.equals("Attachments")) {
                            Intent intent = new Intent(getApplicationContext(),NewItemDescriptionActivity.class);
                            intent.putExtra("name",name);
                            intent.putExtra("parentname",parentName);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(), ItemDescriptionActivity.class);
                            intent.putExtra("new_name", name);
                            intent.putExtra("parentName", parentName);
                            startActivity(intent);
                        }


                    }
                });
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
