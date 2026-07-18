package com.studyvalllet.studyvallet.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private Long id;
    private Long sessionId;
    private Long raterId;
    private String raterName;
    private Long rateeId;
    private String rateeName;
    private int rating;
    private String review;
    private LocalDateTime createdAt;
}
