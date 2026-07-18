package com.studyvalllet.studyvallet.dto;

import com.studyvalllet.studyvallet.entity.SkillSession;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private Long id;
    private Long teacherId;
    private String teacherName;
    private Long learnerId;
    private String learnerName;
    private Long skillId;
    private String skillName;
    private LocalDateTime sessionDate;
    private SkillSession.SessionStatus status;
    private LocalDateTime createdAt;
}
