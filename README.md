(1)Project Title

SkillSwap: Student Peer Skill Exchange Platform

(2) Problem Statement

Students often have skills but lack a structured platform to share or learn from peers. Existing platforms are mostly paid, unidirectional, or not peer-focused. There is a need for a system that connects students based on teachable and learnable skills.

(3) Project Objectives
Build a peer-to-peer skill exchange platform
Enable users to register and manage skills
Provide smart skill matching between users
Implement credit-based reward system
Support session-based learning interactions
Create scalable backend using Spring Boot + MySQL

(4) Modules of the Project
Module Name	Description
User Management Module	Handles user registration, login, and profile management
Skill Management Module	Allows users to add skills they can teach or learn
Skill Matching Module	Matches users based on common skill interests
Credit System Module	Awards credits for teaching and deducts for learning
Session Management Module	Manages booking and tracking of learning sessions
Rating System Module (Future Scope)	Allows users to rate each other after sessions

(5) Database Tables
Table Name	Fields
Users	id (PK), name, email, password, credits
Skills	id (PK), user_id (FK), skill_name, skill_type (TEACH / LEARN)
Sessions (Optional)	id (PK), teacher_id, learner_id, skill_id, session_date, status

(6) Technology Stack
Layer	Technology
Frontend	React.js, HTML, CSS
Backend	Java, Spring Boot
Database	MySQL
Tools	VS Code, Postman

(7) Future Enhancements
AI-based skill recommendation system
Real-time chat between users
Video-based learning sessions
Mobile application support
Certificate generation after completion

 Conclusion

SkillSwap enables students to learn and teach skills in a collaborative environment, promoting peer-to-peer learning and reducing dependency on paid platforms.