package com.studyvalllet.studyvallet.service;

import com.studyvalllet.studyvallet.dto.SessionDTO;
import com.studyvalllet.studyvallet.dto.SessionRequest;
import com.studyvalllet.studyvallet.entity.Credit;
import com.studyvalllet.studyvallet.entity.Skill;
import com.studyvalllet.studyvallet.entity.SkillSession;
import com.studyvalllet.studyvallet.entity.User;
import com.studyvalllet.studyvallet.exception.ResourceNotFoundException;
import com.studyvalllet.studyvallet.repository.CreditRepository;
import com.studyvalllet.studyvallet.repository.SessionRepository;
import com.studyvalllet.studyvallet.repository.SkillRepository;
import com.studyvalllet.studyvallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final CreditRepository creditRepository;

    /**
     * Request a new session.
     */
    @Transactional
    public SessionDTO requestSession(SessionRequest request) {
        User teacher = userRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + request.getTeacherId()));
        User learner = userRepository.findById(request.getLearnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Learner not found with id: " + request.getLearnerId()));
        Skill skill = skillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + request.getSkillId()));

        SkillSession session = new SkillSession();
        session.setTeacher(teacher);
        session.setLearner(learner);
        session.setSkill(skill);
        session.setSessionDate(request.getSessionDate());
        session.setStatus(SkillSession.SessionStatus.PENDING);

        return toDTO(sessionRepository.save(session));
    }

    /**
     * Update session status (ACCEPTED, REJECTED, COMPLETED).
     */
    @Transactional
    public SessionDTO updateStatus(Long sessionId, String status) {
        SkillSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));

        SkillSession.SessionStatus newStatus = SkillSession.SessionStatus.valueOf(status.toUpperCase());
        session.setStatus(newStatus);

        // Award credits when session is completed
        if (newStatus == SkillSession.SessionStatus.COMPLETED) {
            awardCredits(session);
        }

        return toDTO(sessionRepository.save(session));
    }

    /**
     * Award credits to teacher and deduct from learner on session completion.
     */
    private void awardCredits(SkillSession session) {
        User teacher = session.getTeacher();
        User learner = session.getLearner();

        // Teacher earns 5 credits
        teacher.setCredits(teacher.getCredits() + 5);
        userRepository.save(teacher);

        Credit teacherCredit = new Credit();
        teacherCredit.setUser(teacher);
        teacherCredit.setAmount(5);
        teacherCredit.setReason("Taught skill: " + session.getSkill().getSkillName());
        creditRepository.save(teacherCredit);

        // Learner spends 3 credits
        if (learner.getCredits() >= 3) {
            learner.setCredits(learner.getCredits() - 3);
            userRepository.save(learner);

            Credit learnerCredit = new Credit();
            learnerCredit.setUser(learner);
            learnerCredit.setAmount(-3);
            learnerCredit.setReason("Learned skill: " + session.getSkill().getSkillName());
            creditRepository.save(learnerCredit);
        }
    }

    /**
     * Get all sessions for a user (as teacher or learner).
     */
    public List<SessionDTO> getSessionsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return sessionRepository.findAllByUser(user)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get upcoming accepted sessions for a user.
     */
    public List<SessionDTO> getUpcomingSessions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return sessionRepository.findUpcomingByUser(user)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all sessions.
     */
    public List<SessionDTO> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Map SkillSession entity to DTO.
     */
    public SessionDTO toDTO(SkillSession session) {
        return new SessionDTO(
                session.getId(),
                session.getTeacher().getId(),
                session.getTeacher().getName(),
                session.getLearner().getId(),
                session.getLearner().getName(),
                session.getSkill().getId(),
                session.getSkill().getSkillName(),
                session.getSessionDate(),
                session.getStatus(),
                session.getCreatedAt()
        );
    }
}
