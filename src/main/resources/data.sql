DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    activate boolean default true,
    created_time DATETIME NOT NULL
);

INSERT INTO users(id, login, password, email, activate, created_time) VALUES ( 1,'admin','$2y$10$TspWyG/CpKPYf5xVbyBKC.a1Rm.YeKlKiT5spiP4G.c7kxGHCj/Ba','admin',true,CURRENT_DATE);
