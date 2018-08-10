package com.flamezz.pubgcompanion;

public class Feedback {

    private String name;
    private String email;
    private String feedback;
    private float stars;


    Feedback(String name,String email,String feedback,float stars)
    {
        this.name=name;
        this.email=email;
        this.feedback=feedback;
        this.stars=stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
