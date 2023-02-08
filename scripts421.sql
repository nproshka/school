-- CREATE TABLE student
-- (
--     id   serial PRIMARY KEY,
--     name VARCHAR(255) PRIMARY KEY,
--     age  INTEGER check (age >= 16) DEFAULT 20,
--     faculty_id bigint REFERENCES faculty(id)
-- );
--
-- CREATE TABLE faculty
-- (
--     id   serial PRIMARY KEY,
--     name VARCHAR(255) PRIMARY KEY,
--     color  VARCHAR(255) PRIMARY KEY
-- );


ALTER TABLE student
    ADD CONSTRAINT age_constrain CHECK ( age >= 16 );

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE student
    ADD PRIMARY KEY (name);

ALTER TABLE faculty
    ADD PRIMARY KEY (color);

ALTER TABLE faculty
    ADD PRIMARY KEY (name);


