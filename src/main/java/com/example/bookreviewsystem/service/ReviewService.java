package com.example.bookreviewsystem.service;
import com.example.bookreviewsystem.entity.Book;
import com.example.bookreviewsystem.entity.Review;
import com.example.bookreviewsystem.exception.ResourceNotFoundException;
import com.example.bookreviewsystem.repository.BookRepository;
import com.example.bookreviewsystem.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    // Add review for a book
    public Review addReview(Long bookId, Review review){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        review.setBook(book);
        return reviewRepository.save(review);
    }

    //Get all reviews
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    //Get repository by ID
    public Review getReviewById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review cannot find with id: " + id));
    }

    //Update Review
    public Review updateReview(Long id, Review reviewDetails){
        Review review = getReviewById(id);
        review.setReviewerName(reviewDetails.getReviewerName());
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return  reviewRepository.save(review);
    }

    //Delete Review
    public void deleteReview(Long id){
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

    //Search Reviews
    public List<Review> searchReviews(String reviewerName, String keyword){
        if(reviewerName != null) {return reviewRepository.findByReviewerNameContainingIgnoreCase(reviewerName);}
        if(keyword != null) {return reviewRepository.findByCommentContainingIgnoreCase(keyword);}
        return getAllReviews();
    }

    //Get reviews by Book ID
    public List<Review> getReviewsByBookId(Long bookId){
        return reviewRepository.findByBookId(bookId);
    }




}
