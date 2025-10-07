package com.example.bookreviewsystem.controller;

import com.example.bookreviewsystem.entity.Review;
import com.example.bookreviewsystem.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Add review to a book
    @PostMapping("/book/{bookId}")
    public Review addReview(@PathVariable Long bookId, @RequestBody Review review) {
        return reviewService.addReview(bookId, review);
    }

    // Get all reviews or search
    @GetMapping
    public List<Review> getAllReviews(
            @RequestParam(required = false) String reviewerName,
            @RequestParam(required = false) String keyword
    ) {
        return reviewService.getAllReviews(reviewerName, keyword);
    }

    // Get review by ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // Update review safely
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return reviewService.updateReview(id, reviewDetails);
    }

    // Delete review safely
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }

    // Get reviews for a specific book
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }
}
