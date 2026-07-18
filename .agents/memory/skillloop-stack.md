---
name: SkillLoop stack
description: How SkillLoop is built and run — critical for future edits to avoid mistakes.
---

## Stack
- Backend: Spring Boot 3.4 (Java 17), port 8080, package `com.studyvalllet.studyvallet`
- Frontend: React 18 + Vite, port 5000, proxies /api → localhost:8080
- Database: Replit PostgreSQL (env vars PGHOST/PGPORT/PGDATABASE/PGUSER/PGPASSWORD)

## Why PostgreSQL (not MySQL)
MySQL is not natively available on Replit. Switched to Replit's built-in PostgreSQL.

## Critical: Hibernate column naming
Spring Boot's SpringPhysicalNamingStrategy auto-converts camelCase → snake_case.
Do NOT add `@Column(name = "snake_case")` for fields that already match the auto-generated name — Hibernate 6 throws DuplicateMappingException.
Only use `name` in @Column for non-standard names; @JoinColumn is fine.

## Critical: old model/ package
The original project had `model/User.java` and `model/Skill.java`. These were deleted because they conflicted with the new `entity/` package. Never recreate them.

## How to apply
- Before adding @Column(name="x") on any entity field, check if SpringPhysicalNamingStrategy would already produce that name.
- Start workflows in order: Backend API first, then Start application.
