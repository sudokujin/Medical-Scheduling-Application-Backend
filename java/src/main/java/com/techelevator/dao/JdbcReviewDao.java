package com.techelevator.dao;

import com.techelevator.model.Review;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review getReviewByPatientId(int patientId){
        Review review = null;
        String sql = "SELECT * FROM review JOIN patient on review.patient_id = patient.patient_id " +
                "WHERE review.patient_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);

        if(results.next()){
            review = mapRowToReview(results);
        }
        return review;
    }


    @Override
    public List<Review> getReviewsByDoctorId(Integer doctorId){
        List<Review> reviews = new ArrayList<>();
        Review review = null;
        String sql = "SELECT * FROM review JOIN patient on review.patient_id = patient.patient_id " +
                "WHERE review.doctor_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);

        while(results.next()){
            review = mapRowToReview(results);
            reviews.add(review);
        }
        return reviews;
    }



    @Override
    public List<Review> listAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM review;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while(result.next()) {
                reviews.add(mapRowToReview(result));
            }
        } catch (NullValueInNestedPathException | EmptyResultDataAccessException e) {
            throw new RuntimeException("No review found");
        }
        return reviews;
    }


    @Override
    public void createReview(Review review) {
        String sql = "INSERT INTO review(review_title, review_body, review_rating, review_date, doctor_id, patient_id) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, review.getReviewTitle(), review.getReviewBody(), review.getReviewRating(), review.getReviewDate(), review.getDoctorId(), review.getPatientId());
    }


    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM review WHERE review_id=?;";
        jdbcTemplate.update(sql,reviewId);
    }


    private Review mapRowToReview(SqlRowSet result) {
        Review review = new Review();
        review.setReviewId(result.getInt("review_id"));
        review.setReviewTitle(result.getString("review_title"));
        review.setReviewBody(result.getString("review_body"));
        review.setReviewRating(result.getInt("review_rating"));
        review.setReviewDate(LocalDate.parse(result.getString("review_date")));
        review.setPatientId(result.getInt("patient_id"));
        review.setDoctorId(result.getInt("doctor_id"));

        return review;

    }

}

