package com.flamezz.pubgcompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class MapsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_map);
        InitializeControls();
    }

    private void InitializeControls()
    {
        TextView heading_map = findViewById(R.id.heading_map);
        PhotoView map = findViewById(R.id.map);
        ImageView back_button = findViewById(R.id.back_button);
        ImageView image_map = findViewById(R.id.image_map);
        String name = getIntent().getStringExtra("NAME");
        String image = getIntent().getStringExtra("IMAGE");
        String new_map = getIntent().getStringExtra("MAP");
        heading_map.setText(name);
        Picasso.get().load(image).into(image_map);
        Picasso.get().load(new_map).into(map);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"Zoom in for closer View",Toast.LENGTH_LONG).show();
    }
}
