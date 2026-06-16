package com.studyvalllet.studyvallet.controller;

import com.studyvalllet.studyvallet.model.Skill;
import com.studyvalllet.studyvallet.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin("*")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // add skill
    @PostMapping("/add")
    public Skill addSkill(@RequestBody Skill skill) {
        return skillService.addSkill(skill);
    }

    // get all skills
    @GetMapping("/all")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // skill matching API 
    @GetMapping("/match/{skillName}")
    public List<Skill> matchSkill(@PathVariable String skillName) {
        return skillService.findMatchingSkills(skillName);
    }
}