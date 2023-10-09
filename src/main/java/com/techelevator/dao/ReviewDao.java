package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {


    List<Review> getReviewsByDoctorId(Integer doctorId);

    List<Review> listAllReviews();

    Review getReviewByPatientId(int patientId);

    void createReview(Review review);

    void deleteReview(int reviewId);


}
