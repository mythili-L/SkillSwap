package com.studyvalllet.studyvallet.dto;

import com.studyvalllet.studyvallet.entity.Skill;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userAvailability;
    private String skillName;
    private Skill.SkillType skillType;
}
