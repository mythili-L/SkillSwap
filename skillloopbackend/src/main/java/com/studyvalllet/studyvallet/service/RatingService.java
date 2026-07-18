package com.studyvalllet.studyvallet.service;

import com.studyvalllet.studyvallet.dto.RatingDTO;
import com.studyvalllet.studyvallet.dto.RatingRequest;
import com.studyvalllet.studyvallet.entity.Rating;
import com.studyvalllet.studyvallet.entity.SkillSession;
import com.studyvalllet.studyvallet.entity.User;
import com.studyvalllet.studyvallet.exception.ResourceNotFoundException;
import com.studyvalllet.studyvallet.repository.RatingRepository;
import com.studyvalllet.studyvallet.repository.SessionRepository;
import com.studyvalllet.studyvallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    /**
     * Submit a rating for a completed session.
     */
    @Transactional
    public RatingDTO submitRating(RatingRequest request) {
        SkillSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + request.getSessionId()));

        if (session.getStatus() != SkillSession.SessionStatus.COMPLETED) {
            throw new IllegalStateException("Can only rate completed sessions");
        }

        User rater = userRepository.findById(request.getRaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Rater not found with id: " + request.getRaterId()));
        User ratee = userRepository.findById(request.getRateeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ratee not found with id: " + request.getRateeId()));

        // Check if rating already submitted
        if (ratingRepository.findBySessionAndRater(session, rater).isPresent()) {
            throw new IllegalStateException("You have already rated this session");
        }

        Rating rating = new Rating();
        rating.setSession(session);
        rating.setRater(rater);
        rating.setRatee(ratee);
        rating.setRating(request.getRating());
        rating.setReview(request.getReview());

        return toDTO(ratingRepository.save(rating));
    }

    /**
     * Get all ratings received by a user.
     */
    public List<RatingDTO> getRatingsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ratingRepository.findByRatee(user)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all ratings for a session.
     */
    public List<RatingDTO> getRatingsForSession(Long sessionId) {
        SkillSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));
        return ratingRepository.findBySession(session)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get average rating for a user.
     */
    public Double getAverageRating(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Double avg = ratingRepository.findAverageRatingByRatee(user);
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }

    /**
     * Map Rating entity to DTO.
     */
    public RatingDTO toDTO(Rating rating) {
        return new RatingDTO(
                rating.getId(),
                rating.getSession().getId(),
                rating.getRater().getId(),
                rating.getRater().getName(),
                rating.getRatee().getId(),
                rating.getRatee().getName(),
                rating.getRating(),
                rating.getReview(),
                rating.getCreatedAt()
        );
    }
}
