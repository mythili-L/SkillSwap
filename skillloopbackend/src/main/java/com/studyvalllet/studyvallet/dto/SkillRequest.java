package com.studyvalllet.studyvallet.dto;

import com.studyvalllet.studyvallet.entity.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SkillRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Skill name is required")
    private String skillName;

    @NotNull(message = "Skill type is required (TEACH or LEARN)")
    private Skill.SkillType skillType;
}
