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

public class DescriptionActivity extends AppCompatActivity {

    private TextView item1,item2,item3,item4,item5,item6,item7,item8,decription;
    private String name,rootName,parentName;
    private ImageView description_image;
    private String getType,getMagSize,bulletSpeed
            ,range,reload,ammo,shotTime,getDescription;
    private Double getDamage,getCapacity;
    private  String getAttributes;
    private CardView cardView1,cardView2,cardView3,cardView4,cardView5;
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
                 getType = dataSnapshot.child("type").getValue(String.class);
                 getDamage = dataSnapshot.child("damage").getValue(Double.class);
                 getMagSize = dataSnapshot.child("mag size").getValue(String.class);
                 bulletSpeed = dataSnapshot.child("bullet speed").getValue(String.class);
                 range = dataSnapshot.child("range").getValue(String.class);
                 reload = dataSnapshot.child("reload").getValue(String.class);
                 ammo = dataSnapshot.child("ammo").getValue(String.class);
                 shotTime = dataSnapshot.child("shot time").getValue(String.class);
                 getDescription = dataSnapshot.child("description").getValue(String.class);
                 getCapacity = dataSnapshot.child("capacity").getValue(Double.class);
                 getAttributes = dataSnapshot.child("attributes").getValue(String.class);
                Picasso.get().load(getImage).into(description_image);
                Switch(rootName);

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
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.carView2);
        cardView3 = findViewById(R.id.carView3);
        cardView4 = findViewById(R.id.carView4);
        cardView5 = findViewById(R.id.carView5);
        description_backpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    private void Switch(String type)
    {
        switch (type)
        {
            case "Bow & Guns":
                item1.setText("Type:\t"+getType);
                item2.setText(String.valueOf("Damage:\t"+getDamage));
                item3.setText("Mag Size:\t"+getMagSize);
                item4.setText("Bullet Speed:\t"+bulletSpeed);
                item5.setText("Range:\t"+range);
                item6.setText("Reload:\t"+reload);
                item7.setText("Ammo:\t"+ammo);
                item8.setText("Shot Time:\t"+shotTime);
                decription.setText("Description:\n"+getDescription);
                break;

            case "Attachments":
                item1.setText("Type:\t"+getType);
                item2.setText("Capacity:\t"+getCapacity);
                item3.setText("Attributes:\t"+getAttributes);
                item4.setText("Description:\n"+getDescription);
                item5.setVisibility(View.INVISIBLE);
                item6.setVisibility(View.INVISIBLE);
                item7.setVisibility(View.INVISIBLE);
                item8.setVisibility(View.INVISIBLE);
                cardView1.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                cardView4.setVisibility(View.INVISIBLE);
                cardView5.setVisibility(View.INVISIBLE);
                break;

                default:
                    break;
        }
    }
}
