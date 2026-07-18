package com.studyvalllet.studyvallet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingRequest {

    @NotNull(message = "Session ID is required")
    private Long sessionId;

    @NotNull(message = "Rater ID is required")
    private Long raterId;

    @NotNull(message = "Ratee ID is required")
    private Long rateeId;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private int rating;

    private String review;
}
