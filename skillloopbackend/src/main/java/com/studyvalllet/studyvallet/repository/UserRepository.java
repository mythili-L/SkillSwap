package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // Leaderboard: top users by credits
    @Query("SELECT u FROM User u ORDER BY u.credits DESC")
    List<User> findTop10ByOrderByCreditsDesc();
}
