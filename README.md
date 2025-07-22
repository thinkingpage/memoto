# 📝 Memoto – Microblogging Demo Project

**Memoto** is a simplified microblogging application designed as a **learning platform** and **reference project** to systematically explore modern development technologies.
Developed by a Media Informatics graduate to deepen full-stack development expertise.

---

## 📌 Planned Functionality

### 🧾 Memos

- [ ] Create memos (text only)
- [ ] Delete memos
- [ ] (Later) Memos with image attachments
- [ ] Display memos per user (after authentication)

### 📈 Suggestions & Trends

- [ ] Simple endpoint for memo suggestions
- [ ] Kafka consumer processes submitted memos:
  - Extracts frequent words
  - Returns a list of **Trending Words**
- [ ] Frontend component displays trending terms

### 🔐 Authentication (Keycloak)

- [ ] Only logged-in users can create or delete memos

---

## 🧠 Learning Goals

| Area                   | Details                                                  |
|------------------------|----------------------------------------------------------|
| **Spring Boot**        | REST APIs, JPA, Services, DTOs, Tests, Profiles          |
| **PostgreSQL**         | Data modeling, queries, Docker integration               |
| **Angular + TypeScript** | Components, services, HTTP communication, authentication |
| **Keycloak**           | Authentication, JWT, role management                     |
| **Apache Kafka**       | Event-driven architecture, producer/consumer setup       |
| **Docker**             | Containerization of frontend, backend, and services      |
| **CI with GitHub**     | Automated testing, GitHub Actions                        |
| **Deployment**         | Frontend + Backend deployment                            |
| **Documentation**      | README, setup instructions, screenshots                  |

---

## 📁 Project Structure
project-root/<br>
├── backend/ # Spring Boot application + Kafka + PostgreSQL<br>
├── frontend/ # Angular application<br>
├── docker-compose.yml # Local development environment<br>
└── .github/workflows/ # CI pipelines via GitHub Actions<br>


---

## 🚧 Development Status

> This project is developed **iteratively** to gradually integrate each technology.

- ⬜ **Phase 1:** Backend foundation  
- ⬜ **Phase 2:** Frontend  
- ⬜ **Phase 3:** Authentication  
- ⬜ **Phase 4:** Kafka  
- ⬜ **Phase 5:** CI / Testing  
- ⬜ **Phase 6:** Deployment & Documentation  

---

## 🛠 Project Organization

A senior software engineer acts as a technical mentor to ensure production-grade code quality.

Project management follows a real-world team process:

- Tasks tracked via **Trello**  
- Commits contain ticket numbers and are well-scoped  
- Changes are made via **Pull Requests** with code review  
- **Target Test Coverage:**
  - Backend: ≥ 50%
  - Frontend: ≥ 30%

> Coverage goals are moderate to promote practical testing habits without overloading this learning project.

---

## 👨‍🏫 Supervised by

[Sergej But](https://sergejbut.com)

---

## 🖼️ Screenshots

> To be added in a later phase...

---

## 🚀 Setup (Local Development)

## ⚠️ Legal Notice
This is a purely educational demo project. It is not affiliated with or derived from any commercial platform and avoids all brand references or protected terms.
