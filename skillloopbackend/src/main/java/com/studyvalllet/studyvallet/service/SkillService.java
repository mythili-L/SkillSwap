package com.studyvalllet.studyvallet.service;

import com.studyvalllet.studyvallet.model.Skill;
import com.studyvalllet.studyvallet.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // save skill
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // get all skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // MATCHING LOGIC 
    public List<Skill> findMatchingSkills(String skillName) {
        return skillRepository.findBySkillNameIgnoreCase(skillName)
                .stream()
                .filter(skill -> "TEACH".equalsIgnoreCase(skill.getSkillType()))
                .toList();
    }
}