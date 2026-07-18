package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.entity.Rating;
import com.studyvalllet.studyvallet.entity.SkillSession;
import com.studyvalllet.studyvallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByRatee(User ratee);

    List<Rating> findByRater(User rater);

    Optional<Rating> findBySessionAndRater(SkillSession session, User rater);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.ratee = :user")
    Double findAverageRatingByRatee(@Param("user") User user);

    List<Rating> findBySession(SkillSession session);
}
