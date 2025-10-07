package com.example.bookreviewsystem.repository;
import com.example.bookreviewsystem.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewerNameContainingIgnoreCase(String reviewerName);
    List<Review> findByCommentContainingIgnoreCase(String comment);
    List<Review> findByBookId(Long bookId);
}
