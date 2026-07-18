package com.studyvalllet.studyvallet.controller;

import com.studyvalllet.studyvallet.dto.RatingDTO;
import com.studyvalllet.studyvallet.dto.RatingRequest;
import com.studyvalllet.studyvallet.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    // POST /api/ratings
    @PostMapping
    public ResponseEntity<RatingDTO> submitRating(@Valid @RequestBody RatingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.submitRating(request));
    }

    // GET /api/ratings/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.getRatingsForUser(userId));
    }

    // GET /api/ratings/session/{sessionId}
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForSession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(ratingService.getRatingsForSession(sessionId));
    }

    // GET /api/ratings/user/{userId}/average
    @GetMapping("/user/{userId}/average")
    public ResponseEntity<Map<String, Double>> getAverageRating(@PathVariable Long userId) {
        Double avg = ratingService.getAverageRating(userId);
        return ResponseEntity.ok(Map.of("averageRating", avg));
    }
}
