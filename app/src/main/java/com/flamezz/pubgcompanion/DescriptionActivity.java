package com.flamezz.pubgcompanion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    private TextView item1,item2,item3,item4,item5,item6,item7,item8,decription;
    private String name,rootName,parentName;
    private ImageView description_image;
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_description);
        InitializeControls();
        setUpDatabaseReference();
    }

    private void setUpDatabaseReference()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Items = databaseReference.child("Items").child(rootName).child("Subitems").child(parentName).child(name);
        Items.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String getImage = dataSnapshot.child("image").getValue(String.class);
                String getType = dataSnapshot.child("type").getValue(String.class);
                Double getDamage = dataSnapshot.child("damage").getValue(Double.class);
                String getMagSize = dataSnapshot.child("mag size").getValue(String.class);
                String bulletSpeed = dataSnapshot.child("bullet speed").getValue(String.class);
                String range = dataSnapshot.child("range").getValue(String.class);
                String reload = dataSnapshot.child("reload").getValue(String.class);
                String ammo = dataSnapshot.child("ammo").getValue(String.class);
                String shotTime = dataSnapshot.child("shot time").getValue(String.class);
                String getDescription = dataSnapshot.child("description").getValue(String.class);
                Picasso.get().load(getImage).into(description_image);
                item1.setText("Type:\t"+getType);
                item2.setText(String.valueOf("Damage:\t"+getDamage));
                item3.setText("Mag Size:\t"+getMagSize);
                item4.setText("Bullet Speed:\t"+bulletSpeed);
                item5.setText("Range:\t"+range);
                item6.setText("Reload:\t"+reload);
                item7.setText("Ammo:\t"+ammo);
                item8.setText("Shot Time:\t"+shotTime);
                decription.setText("Description:\n"+getDescription);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void InitializeControls()
    {
        name = getIntent().getStringExtra("name");
        rootName = getIntent().getStringExtra("rootName");
        parentName = getIntent().getStringExtra("parentName");
        TextView description_header = findViewById(R.id.description_header);
        description_header.setText(name);
        ImageView description_backpressed = findViewById(R.id.description_backpressed);
        description_image = findViewById(R.id.description_image);
        decription = findViewById(R.id.description);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        item6 = findViewById(R.id.item6);
        item7 = findViewById(R.id.item7);
        item8 = findViewById(R.id.item8);
        description_backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
