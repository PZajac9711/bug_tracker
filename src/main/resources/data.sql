DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS membership;
DROP TABLE IF EXISTS projects;
CREATE TABLE users
(
    id       INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    activate boolean default true,
    created_time DATETIME NOT NULL
);
CREATE TABLE projects(
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(20) NOT NULL UNIQUE,
    created_time DATETIME NOT NULL,
    owner VARCHAR(100) NOT NULL
);
CREATE TABLE membership(
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    project_name VARCHAR(20) NOT NULL,
    role VARCHAR(100) NOT NULL,
    FOREIGN KEY(project_name) REFERENCES projects(project_name)
);

INSERT INTO users(login, password, email, activate, created_time) VALUES ('admin','$2y$10$TspWyG/CpKPYf5xVbyBKC.a1Rm.YeKlKiT5spiP4G.c7kxGHCj/Ba','admin',true,CURRENT_DATE);
