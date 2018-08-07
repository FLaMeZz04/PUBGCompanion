package com.flamezz.pubgcompanion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
 import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;




public class ItemDescriptionActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView Title;
    private TextView des_item1;
    private TextView des_item2;
    private TextView des_item3;
    private TextView des_description;
    private String getName,getParentName,new_usage,new_passengers,new_speed;
    private String new_name,new_type,new_image,new_des,new_attributes;
    private ImageView itemDesImage;
    private CardView cardView;
    private Double new_capacity,new_damage;

    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_item_description);
        InitializeControls();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Items = databaseReference.child("Items").child(getParentName).child("Subitems").child(getName);
        Items.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                new_name = dataSnapshot.child("name").getValue(String.class);
                new_type = dataSnapshot.child("type").getValue(String.class);
                new_capacity = dataSnapshot.child("capacity").getValue(Double.class);
                new_image  = dataSnapshot.child("image").getValue(String.class);
                new_des =  dataSnapshot.child("description").getValue(String.class);
                new_damage = dataSnapshot.child("damage").getValue(Double.class);
                new_usage = dataSnapshot.child("usage").getValue(String.class);
                new_attributes = dataSnapshot.child("attributes").getValue(String.class);
                new_passengers = dataSnapshot.child("passengers").getValue(String.class);
                new_speed = dataSnapshot.child("top speed").getValue(String.class);
                Title.setText(new_name);
                Picasso.get().load(new_image).into(itemDesImage);
                des_item1.setText("Type:\t"+new_type);
                des_item2.setText("Capacity:\t"+String.valueOf(new_capacity));
                des_description.setText("Description:\n\n"+new_des);
                Switch(getParentName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void InitializeControls()
    {

        Title = findViewById(R.id.Title);
        des_item1 = findViewById(R.id.des_item1);
        des_item2 = findViewById(R.id.des_item2);
        des_item3 = findViewById(R.id.des_item3);
        itemDesImage = findViewById(R.id.itemDesImage);
        des_description = findViewById(R.id.des_description);
        cardView = findViewById(R.id.carView);
        getName = getIntent().getStringExtra("new_name");
        getParentName = getIntent().getStringExtra("parentName");
        ImageView onBack = findViewById(R.id.onBack);
        onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void Switch(String type)
    {
        switch(type)
        {
            case "Melee Weapons":
                des_item3.setText("Damage:\t" + new_damage);
                break;


            case "Consumables":
                des_item3.setText("Usage Time:\t" + new_usage);
                break;

            case "Equipments":

                des_item3.setText("Attributes:\t"+new_attributes);
                break;

            case "Vehicles":
                des_item2.setText("Passengers :\t"+new_passengers);
                des_item3.setText("Top Speed:\t"+new_speed);
                break;

            case "Ammunations":
                des_item3.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                break;

            case "Throwables":
                des_item3.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.INVISIBLE);
                break;


            default:
                break;

        }
    }

}
