-- init.sql
CREATE DATABASE IF NOT EXISTS dosi_db;
USE dosi_db;
-- Table des utilisateurs
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des OTP
CREATE TABLE otp_codes (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   user_id BIGINT NOT NULL,
   code VARCHAR(6) NOT NULL,
   expiration DATETIME NOT NULL,
   used BOOLEAN DEFAULT FALSE,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Index pour rechercher rapidement le dernier OTP d'un utilisateur
CREATE INDEX idx_user_id ON otp_codes(user_id);

INSERT INTO users(username, phone)
VALUES ("mariam","0609503757");



