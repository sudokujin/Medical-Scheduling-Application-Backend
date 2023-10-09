package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Review {

    private int reviewId;
    private int patientId;
    private int doctorId;
    private int reviewRating;
    private String reviewTitle;
    private String reviewBody;
    private LocalDate reviewDate;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Review() {

    }


    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", reviewRating=" + reviewRating +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewBody='" + reviewBody + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}

