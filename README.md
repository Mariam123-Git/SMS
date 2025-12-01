# 🔐 Application OTP – Authentification par Code SMS
Application complète permettant l’envoi et la vérification d’un OTP (Code SMS) pour sécuriser la connexion utilisateur.  
Développée avec **Spring Boot**, **React**, **MySQL** et une API SMS externe.

**Pour tester saisisez comme nom:** **mariam**
## 🚀 Fonctionnalités

### 🔵 Backend (Spring Boot)
- Génération d’un OTP à 6 chiffres.
- Limitation d’envoi (1 OTP toutes les 30 secondes).
- Expiration automatique de l’OTP (2 min).
- Vérification sécurisée de l’OTP.
- Envoi SMS via une API externe avec retry.
- Architecture claire (Controller, Service, Repository).

### 🔵 Frontend (React)
- Page séparée : **Demande d’OTP**
- Page séparée : **Vérification OTP**
- Navbar moderne
- Gestion d’erreurs serveur + validations
- Appels API via Axios
# ⚙️ 1. Installation Backend (Spring Boot)

## 📌 Prérequis
- Java 17+
- gradle
- MySQL 8+
- Postman (optionnel)

## 📦 Installer les dépendances
```bash
cd backend
mvn clean install
spring.datasource.url=jdbc:mysql://localhost:3306/otp_db
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

server.port=8080
```

# 📤 2. API Endpoints

## 🔹 Envoyer OTP
**POST** `/auth/send-otp`

### Body :
```json
{
  "username": "mariam"
}
```

### 📌 **Rendu**
# 📤 2. API Endpoints

## 🔹 Envoyer OTP
**POST** `/auth/send-otp`

### Body :
```json
{
  "username": "mariam"
}
```
# 🖥️ 3. Installation Frontend (React)

## 📦 Installer les dépendances
```bash
cd frontend
npm install
```
# 📡 4. Tester avec Postman---url à utiliser sur postman POST http://localhost:8080/auth/send-otp

## 🟣 Envoyer OTP

Body JSON :
```json
{ "username": "mariam" }
```
## Pour Lancer l'application avec Docker 
```
docker compos up build
```
