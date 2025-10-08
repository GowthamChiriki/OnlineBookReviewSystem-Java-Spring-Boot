package com.example.bookreviewsystem.service;

import com.example.bookreviewsystem.dto.ReviewRequestDTO;
import com.example.bookreviewsystem.entity.Book;
import com.example.bookreviewsystem.entity.Review;
import com.example.bookreviewsystem.exception.ResourceNotFoundException;
import com.example.bookreviewsystem.repository.BookRepository;
import com.example.bookreviewsystem.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    // Add review for a book
    @Transactional
    public Review addReview(Long bookId, Review review) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        review.setId(null); // Ensure ID is null for auto-generation
        review.setBook(book);
        return reviewRepository.save(review);
    }

    // Get all reviews (with optional search)
    public List<Review> getAllReviews(String reviewerName, String keyword) {
        if (reviewerName != null) return reviewRepository.findByReviewerNameContainingIgnoreCase(reviewerName);
        if (keyword != null) return reviewRepository.findByCommentContainingIgnoreCase(keyword);
        return reviewRepository.findAll();
    }

    // Get review by ID
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    // Update review safely
    @Transactional
    public Review updateReview(Long id, Review reviewDetails) {
        Review existingReview = getReviewById(id);

        existingReview.setReviewerName(reviewDetails.getReviewerName());
        existingReview.setRating(reviewDetails.getRating());
        existingReview.setComment(reviewDetails.getComment());

        return reviewRepository.save(existingReview);
    }

    // Delete review safely

    @Transactional
    public void deleteReview(Long id) {
        Review existingReview = getReviewById(id);
        reviewRepository.delete(existingReview);
    }

    // Get all reviews for a specific book
    public List<Review> getReviewsByBookId(Long bookId) {

        return reviewRepository.findByBookId(bookId);
    }

    public void saveAllReviews(List<ReviewRequestDTO> reviewRequests) {
        List<Review> reviewsToSave = new ArrayList<>();

        for (ReviewRequestDTO req : reviewRequests) {
            Book book = bookRepository.findById(req.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found with ID " + req.getBookId()));

            Review review = new Review();
            review.setReviewerName(req.getReviewerName());
            review.setRating(req.getRating());
            review.setComment(req.getComment());
            review.setBook(book);

            reviewsToSave.add(review);
        }

        reviewRepository.saveAll(reviewsToSave);
    }
}
