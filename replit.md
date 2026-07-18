# SkillLoop – Peer Skill Exchange Platform

## Project Overview

Full-stack peer skill exchange app for students. Built with Spring Boot (Java 17) backend and React + Vite frontend. Students can register, list teachable/learnable skills, find matches, book sessions, rate each other, earn credits, and appear on a leaderboard.

## Architecture

- **Frontend**: React 18 + Vite on port 5000. Proxies `/api/*` to the backend.
- **Backend**: Spring Boot 3.4 (Java 17) REST API on port 8080.
- **Database**: Replit-managed PostgreSQL. Tables auto-created by Hibernate on startup.

## Running the App

Two workflows must both be running:
1. **Backend API** — `cd skillloopbackend && mvn spring-boot:run`
2. **Start application** — `cd skillloopfrontend && npm run dev`

Start the backend first; the frontend proxies API calls to it.

## Key Files

| Path | Purpose |
|------|---------|
| `skillloopbackend/src/main/resources/application.properties` | DB config (uses Replit PG env vars) |
| `skillloopbackend/src/main/java/com/studyvalllet/studyvallet/` | All Java source |
| `skillloopfrontend/src/services/api.js` | All Axios API calls |
| `skillloopfrontend/src/context/AuthContext.jsx` | Auth state (localStorage) |
| `skillloopfrontend/vite.config.js` | Vite + proxy config |

## Environment Variables

The backend uses Replit's managed PostgreSQL environment variables automatically:
- `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`

## User Preferences

- Keep existing package structure; do not migrate to a different monorepo layout without asking.
- Use PostgreSQL (Replit native) — not MySQL.
