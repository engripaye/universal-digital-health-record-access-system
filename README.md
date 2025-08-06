# 🩺 Universal Digital Health Record Access System (HealthChain)

A secure, interoperable platform for managing and sharing **digital health records** between patients, healthcare providers, and authorized third parties, regardless of location or system differences.

This system uses **OAuth2.0 authentication**, **role-based access control**, and **end-to-end encryption** to ensure privacy and compliance with **HIPAA** and **GDPR** standards.

<img width="1536" height="1024" alt="Universal Health Record Access System" src="https://github.com/user-attachments/assets/d5148afe-d920-484f-a5af-78467736770c" />

---

## 📖 Table of Contents

1. [Overview](#-overview)
2. [Features](#-features)
3. [System Architecture](#-system-architecture)
4. [Tech Stack](#-tech-stack)
5. [Setup & Installation](#-setup--installation)
6. [Usage Guide](#-usage-guide)
7. [Security & Compliance](#-security--compliance)
8. [API Endpoints](#-api-endpoints)
9. [Screenshots](#-screenshots)
10. [Future Enhancements](#-future-enhancements)
11. [License](#-license)

---

## 🌐 Overview

The **Universal Digital Health Record Access System** allows **patients** to control their medical data and securely share it with **healthcare providers** anywhere in the world.
By leveraging **OAuth2 Authorization Code flow**, **interoperable data standards** like **FHIR (Fast Healthcare Interoperability Resources)**, and **secure cloud storage**, the system ensures that health records are **accessible, portable, and safe**.

---

## ✨ Features

✅ **Secure Authentication** – OAuth2.0 & OpenID Connect for patient and provider login.
✅ **Role-Based Access Control** – Different permissions for Patients, Doctors, and Admins.
✅ **Interoperability** – FHIR-compliant API for healthcare system integration.
✅ **Audit Logging** – Tracks every record access and change.
✅ **Encrypted Storage** – AES-256 encryption for health data at rest and TLS 1.3 for data in transit.
✅ **Consent Management** – Patients grant/revoke access to their records.
✅ **Cross-Border Access** – Works across countries with data localization awareness.
✅ **Disaster Recovery Ready** – Backups and replication ensure records are never lost.

---

## 🏗 System Architecture

```plaintext
                 ┌────────────────────────────┐
                 │        Patient App          │
                 │  (Web / Mobile Interface)   │
                 └──────────────┬─────────────┘
                                │
                                ▼
                 ┌────────────────────────────┐
                 │     OAuth2 Auth Server      │
                 │  (e.g., Auth0 / Keycloak)   │
                 └──────────────┬─────────────┘
                                │
                                ▼
                 ┌────────────────────────────┐
                 │    API Gateway (Spring)     │
                 │   Access Control + Routing  │
                 └──────────────┬─────────────┘
                                │
          ┌─────────────────────┼─────────────────────┐
          ▼                     ▼                     ▼
┌────────────────┐    ┌─────────────────────┐   ┌────────────────┐
│ Patient Service │    │ Health Record Svc   │   │ Audit Service  │
│ (Profile Mgmt)  │    │ (FHIR API & Storage)│   │ (Logging/Track)│
└────────────────┘    └─────────────────────┘   └────────────────┘
```

---

## 🛠 Tech Stack

**Backend:**

* Java 21
* Spring Boot 3.5
* Spring Security (OAuth2 Resource Server & Client)
* Hibernate / JPA
* MySQL / PostgreSQL (configurable)
* Redis (caching)

**Frontend:**

* React.js (Web) / React Native (Mobile)
* TailwindCSS / Material UI

**Security & Compliance:**

* OAuth2.0 + OpenID Connect
* JWT Access Tokens
* AES-256 / TLS 1.3 Encryption
* HIPAA & GDPR compliance

**DevOps:**

* Docker & Kubernetes (Deployment)
* Jenkins CI/CD
* Nginx (Reverse Proxy)

---

## ⚙ Setup & Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/universal-digital-health-record-access-system.git
cd universal-digital-health-record-access-system
```

### 2️⃣ Configure Environment Variables

Create an `.env` file in the root directory:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/healthchain
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=yourpassword
OAUTH2_CLIENT_ID=your_client_id
OAUTH2_CLIENT_SECRET=your_client_secret
JWT_SECRET=your_jwt_secret
```

### 3️⃣ Build & Run Backend

```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Run Frontend

```bash
cd frontend
npm install
npm start
```

---

## 📚 Usage Guide

1. **Sign Up / Log In** using OAuth2.0 provider (Google, Auth0, etc.)
2. **Complete Profile Setup** (personal + medical history).
3. **Upload Medical Records** (PDF, Images, Structured Data).
4. **Grant Access** to healthcare providers or organizations.
5. **View & Track** every access request in your **Audit Logs**.

---

## 🔐 Security & Compliance

* **Authentication:** OAuth2.0 + OpenID Connect
* **Authorization:** RBAC (Patient, Doctor, Admin)
* **Encryption:**

  * At Rest → AES-256
  * In Transit → TLS 1.3
* **Audit Trail:** Immutable logs of all access events
* **Standards:** FHIR R4, HIPAA, GDPR

---

## 📡 API Endpoints

| Method | Endpoint                  | Description                |
| ------ | ------------------------- | -------------------------- |
| POST   | `/api/auth/login`         | OAuth2 login               |
| GET    | `/api/patient/{id}`       | Get patient profile        |
| POST   | `/api/patient/records`    | Upload health record       |
| GET    | `/api/records/{recordId}` | Retrieve health record     |
| POST   | `/api/consent/grant`      | Grant access to a provider |
| POST   | `/api/consent/revoke`     | Revoke provider access     |

---

## 📷 Screenshots

*(Add screenshots of the login page, dashboard, record viewer, and consent management page here)*

---

## 🚀 Future Enhancements

* AI-powered **medical data analysis** for early disease detection.
* Blockchain-based **immutable health record ledger**.
* Support for **offline access** with secure sync.
* Integration with **wearable devices** for live health metrics.

---

## 📜 License

This project is licensed under the **MIT License** – you’re free to use, modify, and distribute with attribution.

---


