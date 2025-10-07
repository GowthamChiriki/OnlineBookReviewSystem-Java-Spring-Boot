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

    @PostMapping("/book/{bookId}")
    public Review addReview(@PathVariable Long bookId, @RequestBody Review review) {
        return reviewService.addReview(bookId, review);
    }

    @GetMapping
    public List<Review> getAllReviews(
            @RequestParam(required = false) String reviewerName,
            @RequestParam(required = false) String keyword
    ) {
        return reviewService.searchReviews(reviewerName, keyword);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }
}
