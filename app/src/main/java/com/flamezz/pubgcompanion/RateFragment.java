package com.flamezz.pubgcompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment {


    public RateFragment() {
        // Required empty public constructor
    }
   private Float stars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_rate, container, false);
        final EditText feedback_name = view.findViewById(R.id.feedback_name);
        final EditText feedback_email = view.findViewById(R.id.feedback_email);
        final EditText feedback = view.findViewById(R.id.feedback);
        Button submitButton = view.findViewById(R.id.submitButton);
        final RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    stars = ratingBar.getRating();
                    Toast.makeText(getContext(),"You have Given\t"+stars,Toast.LENGTH_LONG).show();
                    ratingBar.setIsIndicator(true);


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
                    ratingBar.setIsIndicator(false);
                }
                else
                {
                    feedback_name.setError("Cannot be Empty");
                    feedback_email.setError("Cannot be Empty");
                    feedback.setError("Cannot be Empty");
                    ratingBar.setIsIndicator(true);
                }
            }
        });



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"We will take 2 minutes of yours :)",Toast.LENGTH_LONG).show();
    }
}
