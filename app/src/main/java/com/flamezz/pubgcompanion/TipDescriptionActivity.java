package com.flamezz.pubgcompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TipDescriptionActivity extends AppCompatActivity {

    private TextView description_tricks,title_tricks;
    private ImageView image_tricks;
    private ImageView goBack;
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_tip_description);
        InitializeControls();
    }


    private void InitializeControls()
    {
        description_tricks = findViewById(R.id.description_tricks);
        title_tricks = findViewById(R.id.title_tricks);
        image_tricks = findViewById(R.id.image_tricks);
        String setName = getIntent().getStringExtra("name");
        String setDescription = getIntent().getStringExtra("description");
        String setImage = getIntent().getStringExtra("image");
        title_tricks.setText(setName);
        description_tricks.setText(setDescription);
        Picasso.get().load(setImage).into(image_tricks);
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
