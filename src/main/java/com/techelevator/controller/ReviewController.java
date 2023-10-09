package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/reviews")
@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class ReviewController {
    private final ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    @GetMapping("/{id}")
    public Review getReviewByPatientId(@PathVariable int id) {
        return reviewDao.getReviewByPatientId(id);
    }

    @GetMapping("/doctor/{id}}")
    public List<Review> getReviewsByDoctorId(@PathVariable Integer id) {
        return reviewDao.getReviewsByDoctorId(id);
    }

    @GetMapping("")
    public List<Review> getAllReviews() {
        return reviewDao.listAllReviews();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createReview(@Valid @RequestBody Review review) {
        reviewDao.createReview(review);
    }
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewDao.deleteReview(id);
    }
}
