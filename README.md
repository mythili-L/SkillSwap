# SkillLoop – Peer Skill Exchange Platform

A full-stack web application where students exchange skills without monetary transactions. Students register, add teachable/learnable skills, find matches, book sessions, give ratings, earn credits, and appear on a leaderboard.

## Technology Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React 18 + Vite, React Router 6, Axios |
| Backend | Java 17 + Spring Boot 3.4, Spring Security, Spring Data JPA |
| Database | PostgreSQL (Replit managed) |
| ORM | Hibernate 6 / Spring Data JPA |

## Project Structure

```
skillloopbackend/          ← Spring Boot API (port 8080)
  src/main/java/com/studyvalllet/studyvallet/
    entity/               User, Skill, SkillSession, Rating, Credit
    repository/           JPA repositories
    service/              Business logic
    controller/           REST controllers
    dto/                  Request/Response objects
    config/               CORS + Security
    exception/            Global error handler

skillloopfrontend/         ← React + Vite app (port 5000)
  src/
    pages/                Home, Register, Login, Dashboard, Profile,
                          AddSkill, MySkills, SkillMatch, BookSession,
                          Sessions, Ratings, Leaderboard
    components/           Navbar, Sidebar, Footer, Alert, LoadingSpinner
    services/api.js       Axios API client
    context/AuthContext   Auth state management
```

## Running the App

**Backend** (must start first):
```bash
cd skillloopbackend
mvn spring-boot:run
# Runs on port 8080
```

**Frontend**:
```bash
cd skillloopfrontend
npm install
npm run dev
# Runs on port 5000, proxies /api → localhost:8080
```

Or use the configured Replit workflows: **Backend API** and **Start application**.

## API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register new user |
| POST | `/api/users/login` | Login |
| GET | `/api/users/{id}` | Get user profile |
| PUT | `/api/users/{id}` | Update profile |
| GET | `/api/users/leaderboard` | Top 10 by credits |

### Skills
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/skills` | Add skill |
| PUT | `/api/skills/{id}` | Update skill |
| DELETE | `/api/skills/{id}` | Delete skill |
| GET | `/api/skills/user/{userId}` | User's skills |
| GET | `/api/skills/match?skillName=X` | Find teachers for a skill |

### Sessions
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/sessions` | Request a session |
| PATCH | `/api/sessions/{id}/status` | Accept/Reject/Complete |
| GET | `/api/sessions/user/{userId}` | User's sessions |
| GET | `/api/sessions/user/{userId}/upcoming` | Upcoming sessions |

### Ratings
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/ratings` | Submit rating |
| GET | `/api/ratings/user/{userId}` | Ratings received |
| GET | `/api/ratings/user/{userId}/average` | Average rating |

## Credit System

- New users start with **10 credits**
- Teaching a session: **+5 credits** awarded
- Learning a session: **−3 credits** deducted

## Database Tables

All tables are auto-created by Hibernate on startup:
- `users` — id, name, email, password (BCrypt), credits, availability
- `skills` — id, user_id, skill_name, skill_type (TEACH/LEARN)
- `skill_sessions` — id, teacher_id, learner_id, skill_id, session_date, status
- `ratings` — id, session_id, rater_id, ratee_id, rating (1–5), review
- `credits` — id, user_id, amount, reason (audit log)
