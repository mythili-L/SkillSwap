package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findBySkillNameIgnoreCase(String skillName);

    List<Skill> findBySkillType(String skillType);
}
