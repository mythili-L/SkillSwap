package com.studyvalllet.studyvallet.controller;

import com.studyvalllet.studyvallet.dto.SessionDTO;
import com.studyvalllet.studyvallet.dto.SessionRequest;
import com.studyvalllet.studyvallet.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    // POST /api/sessions
    @PostMapping
    public ResponseEntity<SessionDTO> requestSession(@Valid @RequestBody SessionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.requestSession(request));
    }

    // PATCH /api/sessions/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<SessionDTO> updateStatus(@PathVariable Long id,
                                                    @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sessionService.updateStatus(id, status));
    }

    // GET /api/sessions/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SessionDTO>> getSessionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(sessionService.getSessionsByUser(userId));
    }

    // GET /api/sessions/user/{userId}/upcoming
    @GetMapping("/user/{userId}/upcoming")
    public ResponseEntity<List<SessionDTO>> getUpcomingSessions(@PathVariable Long userId) {
        return ResponseEntity.ok(sessionService.getUpcomingSessions(userId));
    }

    // GET /api/sessions
    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
