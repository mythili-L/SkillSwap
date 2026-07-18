package com.studyvalllet.studyvallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionRequest {

    @NotNull(message = "Teacher ID is required")
    private Long teacherId;

    @NotNull(message = "Learner ID is required")
    private Long learnerId;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    private LocalDateTime sessionDate;
}
