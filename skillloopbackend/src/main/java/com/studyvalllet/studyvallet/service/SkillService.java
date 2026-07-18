package com.studyvalllet.studyvallet.service;

import com.studyvalllet.studyvallet.dto.SkillDTO;
import com.studyvalllet.studyvallet.dto.SkillRequest;
import com.studyvalllet.studyvallet.entity.Skill;
import com.studyvalllet.studyvallet.entity.User;
import com.studyvalllet.studyvallet.exception.ResourceNotFoundException;
import com.studyvalllet.studyvallet.repository.SkillRepository;
import com.studyvalllet.studyvallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    /**
     * Add a new skill for a user.
     */
    @Transactional
    public SkillDTO addSkill(SkillRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
        Skill skill = new Skill();
        skill.setUser(user);
        skill.setSkillName(request.getSkillName());
        skill.setSkillType(request.getSkillType());
        return toDTO(skillRepository.save(skill));
    }

    /**
     * Update an existing skill.
     */
    @Transactional
    public SkillDTO updateSkill(Long skillId, SkillRequest request) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + skillId));
        skill.setSkillName(request.getSkillName());
        skill.setSkillType(request.getSkillType());
        return toDTO(skillRepository.save(skill));
    }

    /**
     * Delete a skill by ID.
     */
    @Transactional
    public void deleteSkill(Long skillId) {
        if (!skillRepository.existsById(skillId)) {
            throw new ResourceNotFoundException("Skill not found with id: " + skillId);
        }
        skillRepository.deleteById(skillId);
    }

    /**
     * Get all skills for a specific user.
     */
    public List<SkillDTO> getSkillsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return skillRepository.findByUser(user)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all skills.
     */
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find matching teachers for a given skill name.
     * Returns users who can TEACH the requested skill.
     */
    public List<SkillDTO> findMatches(String skillName) {
        return skillRepository.findBySkillNameContainingIgnoreCaseAndSkillType(skillName, Skill.SkillType.TEACH)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Map Skill entity to DTO.
     */
    public SkillDTO toDTO(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getUser().getId(),
                skill.getUser().getName(),
                skill.getUser().getEmail(),
                skill.getUser().getAvailability(),
                skill.getSkillName(),
                skill.getSkillType()
        );
    }
}
