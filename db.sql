CREATE TABLE cities
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    city VARCHAR(128) NOT NULL
);
CREATE UNIQUE INDEX unique_city ON cities (city);
CREATE UNIQUE INDEX unique_id ON cities (id);
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

