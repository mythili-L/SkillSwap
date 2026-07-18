package com.studyvalllet.studyvallet.controller;

import com.studyvalllet.studyvallet.dto.SkillDTO;
import com.studyvalllet.studyvallet.dto.SkillRequest;
import com.studyvalllet.studyvallet.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    // POST /api/skills
    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@Valid @RequestBody SkillRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.addSkill(request));
    }

    // PUT /api/skills/{id}
    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id,
                                                 @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.updateSkill(id, request));
    }

    // DELETE /api/skills/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok(Map.of("message", "Skill deleted successfully"));
    }

    // GET /api/skills/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillDTO>> getSkillsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(skillService.getSkillsByUser(userId));
    }

    // GET /api/skills
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // GET /api/skills/match?skillName=Java
    @GetMapping("/match")
    public ResponseEntity<List<SkillDTO>> findMatches(@RequestParam String skillName) {
        return ResponseEntity.ok(skillService.findMatches(skillName));
    }
}
