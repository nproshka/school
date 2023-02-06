CREATE TABLE student
(
    id   serial PRIMARY KEY,
    name VARCHAR(255) PRIMARY KEY,
    age  INTEGER check (age >= 16) DEFAULT 20,
    faculty_id bigint REFERENCES faculty(id)
);

CREATE TABLE faculty
(
    id   serial PRIMARY KEY,
    name VARCHAR(255) PRIMARY KEY,
    color  VARCHAR(255) PRIMARY KEY
);
