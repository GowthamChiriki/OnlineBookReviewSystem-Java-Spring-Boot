package com.example.bookreviewsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Reviewer Name is required")
    private String reviewerName;

    @Min(1)
    @Max(5)
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


}
