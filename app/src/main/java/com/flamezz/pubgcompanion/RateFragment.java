package com.flamezz.pubgcompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment{


    public RateFragment() {
        // Required empty public constructor
    }
   private Float stars;
    private ImageView smiley;
    private RatingBar ratingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_rate, container, false);
        final EditText feedback_name = view.findViewById(R.id.feedback_name);
        final EditText feedback_email = view.findViewById(R.id.feedback_email);
        final EditText feedback = view.findViewById(R.id.feedback);
        Button submitButton = view.findViewById(R.id.submitButton);
         ratingBar = view.findViewById(R.id.ratingBar);
        smiley = view.findViewById(R.id.smiley);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    stars = ratingBar.getRating();
                    ratingBar.setIsIndicator(true);
                    int numStars = (int) ratingBar.getRating();
                    switch (numStars)
                    {
                        case 1:
                            smiley.setVisibility(View.VISIBLE);
                            smiley.setImageResource(R.drawable.smiley_1);
                            break;
                        case 2:
                            smiley.setVisibility(View.VISIBLE);
                            smiley.setImageResource(R.drawable.smiley_2);
                            break;
                        case 3:
                            smiley.setVisibility(View.VISIBLE);
                            smiley.setImageResource(R.drawable.smiley_3);
                            break;
                        case 4:
                            smiley.setVisibility(View.VISIBLE);
                            smiley.setImageResource(R.drawable.smiley_4);
                            break;
                        case 5:
                            smiley.setVisibility(View.VISIBLE);
                            smiley.setImageResource(R.drawable.smiley_5);
                            break;



                    }



                }
        });
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = feedback_name.getText().toString();
                String getEmail = feedback_email.getText().toString();
                String getFeedback = feedback.getText().toString();
                Feedback feed = new Feedback(getName,getEmail,getFeedback,stars);
                if(!getName.isEmpty() && !getEmail.isEmpty() && !getFeedback.isEmpty() && ratingBar.getRating()!=0)
                {
                    Toast.makeText(getContext(),"Thank you for your valuable feedback",Toast.LENGTH_LONG).show();
                    databaseReference.child(feed.getName()).setValue(feed);
                    feedback_name.setText("");
                    feedback_email.setText("");
                    feedback.setText("");
                    smiley.setVisibility(View.INVISIBLE);
                    ratingBar.setRating(0);
                    ratingBar.setIsIndicator(false);


                }
                else
                {
                    feedback_name.setError("Cannot be Empty");
                    feedback_email.setError("Cannot be Empty");
                    feedback.setError("Cannot be Empty");

                }
            }
        });



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"We will take 2 minutes of yours :)",Toast.LENGTH_LONG).show();
        ratingBar.setRating(0);
        ratingBar.setIsIndicator(false);
        smiley.setVisibility(View.INVISIBLE);
    }


}
