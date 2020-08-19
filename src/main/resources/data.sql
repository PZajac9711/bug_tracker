DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS membership;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS tasks;
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
    FOREIGN KEY(project_name) REFERENCES projects(project_name),
    FOREIGN KEY(login) REFERENCES users(login)
);
CREATE TABLE tasks(
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    task_description VARCHAR(50) NOT NULL,
    project_name VARCHAR(20) NOT NULL,
    assign_to VARCHAR(100),
    done boolean default false,
    approved boolean default false,
    task_details VARCHAR(350)
);
INSERT INTO users(login, password, email, activate, created_time) VALUES ('admin','$2y$10$TspWyG/CpKPYf5xVbyBKC.a1Rm.YeKlKiT5spiP4G.c7kxGHCj/Ba','patzaj9711@gmail.com',true,CURRENT_DATE);
INSERT INTO users(login, password, email, activate, created_time) VALUES ('adam','$2y$10$TspWyG/CpKPYf5xVbyBKC.a1Rm.YeKlKiT5spiP4G.c7kxGHCj/Ba','adam',true,CURRENT_DATE);
INSERT INTO projects(project_name, created_time, owner) VALUES ( 'hello',CURRENT_DATE,'admin');
INSERT INTO membership(login, project_name, role) VALUES ( 'admin','hello','not implemented' );
INSERT INTO membership(login, project_name, role) VALUES ( 'adam','hello','not implemented' );
INSERT INTO tasks(task_description, project_name,task_details) VALUES ( 'task1','hello','details about task1');
INSERT INTO tasks(task_description, project_name) VALUES ( 'task2','hello');
INSERT INTO tasks(task_description, project_name, assign_to) VALUES ( 'task3','hello', 'admin');
INSERT INTO tasks(task_description, project_name, assign_to,done) VALUES ( 'task4','hello', 'admin',true);
INSERT INTO tasks(task_description, project_name, assign_to,done,approved) VALUES ( 'task5','hello', 'admin',true,true);
