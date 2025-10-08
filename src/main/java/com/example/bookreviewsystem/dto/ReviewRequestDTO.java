package com.example.bookreviewsystem.dto;

import lombok.Data;

@Data

public class ReviewRequestDTO {
    private Long bookId;
    private String reviewerName;
    private int rating;
    private String comment;
}
