CREATE TABLE cities
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    city VARCHAR(128) NOT NULL
);
CREATE TABLE directions
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    departure VARCHAR(256) NOT NULL,
    dep_date DATETIME NOT NULL,
    destination VARCHAR(256) NOT NULL,
    dest_date DATETIME NOT NULL,
    basic_price DOUBLE NOT NULL,
    date_multiplier DOUBLE DEFAULT 1 NOT NULL,
    fill_multiplier DOUBLE DEFAULT 1 NOT NULL,
    capacity INT NOT NULL,
    left_places INT
);
CREATE TABLE orders
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    direction INT,
    quantity INT DEFAULT 1 NOT NULL,
    baggage TINYINT DEFAULT 0 NOT NULL,
    priority_queue TINYINT DEFAULT 0 NOT NULL,
    client INT NOT NULL,
    summa DOUBLE NOT NULL,
    paid TINYINT DEFAULT 0 NOT NULL
);
CREATE TABLE roles
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role VARCHAR(60) NOT NULL
);
CREATE TABLE user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL,
    password VARCHAR(256) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL,
    patronomic VARCHAR(128) NOT NULL,
    role INT NOT NULL
);
CREATE UNIQUE INDEX unique_city ON cities (city);
CREATE UNIQUE INDEX unique_id ON cities (id);
CREATE UNIQUE INDEX unique_role ON roles (role);
CREATE UNIQUE INDEX unique_email ON user (email);
CREATE UNIQUE INDEX unique_id ON user (id);
