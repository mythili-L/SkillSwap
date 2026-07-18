package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.entity.Skill;
import com.studyvalllet.studyvallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByUser(User user);

    List<Skill> findByUserId(Long userId);

    List<Skill> findBySkillType(Skill.SkillType skillType);

    // Match by skill name (case-insensitive) where type is TEACH
    List<Skill> findBySkillNameContainingIgnoreCaseAndSkillType(String skillName, Skill.SkillType skillType);

    List<Skill> findBySkillNameContainingIgnoreCase(String skillName);
}
