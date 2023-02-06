CREATE TABLE humans
(
    id serial PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    age     INTEGER check (age > 0) DEFAULT 18,
    license BOOLEAN,
    car_id INTEGER REFERENCES cars (id)
);

CREATE TABLE cars
(
    id serial PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price INTEGER

);
