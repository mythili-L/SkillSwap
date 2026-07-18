package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.entity.SkillSession;
import com.studyvalllet.studyvallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SkillSession, Long> {

    List<SkillSession> findByTeacher(User teacher);

    List<SkillSession> findByLearner(User learner);

    @Query("SELECT s FROM SkillSession s WHERE s.teacher = :user OR s.learner = :user ORDER BY s.createdAt DESC")
    List<SkillSession> findAllByUser(@Param("user") User user);

    @Query("SELECT s FROM SkillSession s WHERE (s.teacher = :user OR s.learner = :user) AND s.status = 'ACCEPTED' ORDER BY s.sessionDate ASC")
    List<SkillSession> findUpcomingByUser(@Param("user") User user);
}
