package com.akrisht.PulseReviewScraper.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Review {
    private String title;
    private String description;
    private String date;
    private String reviewer;
    private double rating;
    public Review(){

    }

    public Review(String title, String description, String date, String reviewer, double rating) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.reviewer = reviewer;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
