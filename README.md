# 📝 Memoto – Microblogging Demo Project

**Memoto** is a simplified microblogging application designed as a **learning platform** and **reference project** to systematically explore modern development technologies.
Developed by a Media Informatics graduate to deepen full-stack development expertise.

---

## 📌 Planned Functionality

### 🧾 Memos

- [x] Create memos (text only)
- [x] Delete memos
- [ ] (Later) Memos with image attachments
- [ ] Display memos per user (after authentication)

### 🔐 Authentication (Keycloak)

- [x] Only logged-in users can create or delete memos

### 📈 Suggestions & Trends

- [ ] Simple endpoint for memo suggestions
- [ ] Frontend component displays trending terms

---

## 🧠 Learning Goals

| Area                   | Details                                                  |
|------------------------|----------------------------------------------------------|
| **Spring Boot**        | REST APIs, JPA, Services, DTOs, Tests, Profiles          |
| **PostgreSQL**         | Data modeling, queries, Docker integration               |
| **Angular + TypeScript** | Components, services, HTTP communication, authentication |
| **Keycloak**           | Authentication, JWT, role management                     |
| **Docker**             | Containerization of frontend, backend, and services      |
| **Deployment**         | Frontend + Backend deployment                            |
| **Documentation**      | README, setup instructions, screenshots                  |

---

## 📁 Project Structure

project-root/<br>
├── backend/ # Spring Boot application and the docker-compose.yml<br>
├── frontend/ # Angular application<br>

---

## 👨‍🏫 Supervised by

A senior software engineer acts as a technical mentor to ensure production-grade code quality.
[Sergej But](https://sergejbut.com)

---

## 🚀 Setup (Local Development)

```
 1. cd /backend and docker-compose up
 2. add a new keycloak realm, client and user under the port 8080 (memoto-realm and memoto-client)
 3. cd /frontend and npm install
 4. start the frontend (ng serve) and backend
```

---

## 🖼️ Screenshots

> The following screenshot shows the target design from Figma, not the current state.

> ![memoto-goal](https://github.com/user-attachments/assets/1b9c3f5d-5aa3-449d-9e9b-5f130b7db0e0)

---

## ⚠️ Legal Notice
This is a purely educational demo project. It is not affiliated with or derived from any commercial platform and avoids all brand references or protected terms.
