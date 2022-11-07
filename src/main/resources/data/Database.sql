CREATE
    DATABASE IF NOT EXISTS rostyks;
USE `rostyks`;

DROP TABLE IF EXISTS driver_has_car;
DROP TABLE IF EXISTS trip;
DROP TABLE IF EXISTS type_of_order;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS driver;


CREATE TABLE driver
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(50) NOT NULL,
    rating           INT         NOT NULL,
    completed_orders INT         NOT NULL,
    is_vacant        TINYINT     NOT NULL
);

CREATE TABLE car
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    clas  VARCHAR(50) NOT NULL
);

CREATE TABLE driver_has_car
(
    driver_id INT NOT NULL,
    car_id    INT NOT NULL,
    PRIMARY KEY (driver_id, car_id),
    CONSTRAINT FOREIGN KEY (driver_id) REFERENCES driver (id),
    CONSTRAINT FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE user
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(50),
    rating INT NOT NULL,
    type_of_order_id INT NOT NULL
);

CREATE TABLE trip
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    start_point VARCHAR(50) NOT NULL,
    end_point   VARCHAR(50) NOT NULL,
    driver_id   INT         NOT NULL,
    user_id     INT         NOT NULL,
    CONSTRAINT FOREIGN KEY (driver_id) REFERENCES driver (id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE type_of_order
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

insert into `car`
values (1, 'Toyota1', 'middle'),
       (2, 'Toyota2', 'premium'),
       (3, 'Toyota3', 'classic'),
       (4, 'Toyota4', 'middle'),
       (5, 'Toyota5', 'premium'),
       (6, 'Toyota6', 'middle'),
       (7, 'Toyota7', 'middle');

insert into `user`
values (1, 'Sofia', 5, 1),
       (2, 'Mary', 5, 3),
       (3, 'Oksana', 5, 2),
       (4, 'Lesya', 5, 2),
       (5, 'Zenoviy', 5, 4),
       (6, 'Liuda', 5, 1),
       (7, 'Bogdan', 5, 3),
       (8, 'Andriana', 5, 4);

insert into `driver`
values (1, 'Roman', 5, 56, 0),
       (2, 'Rostyk', 5, 565, 0),
       (3, 'John', 5, 576, 0),
       (4, 'Mike', 5, 596, 0),
       (5, 'Steve', 5, 586, 0);

insert into `driver_has_car`
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (1, 4);

insert into `trip`
values (1, 'a', 'b', 1, 1),
       (2, 'c', 'd', 2, 1),
       (3, 'e', 'f', 1, 2),
       (4, 'g', 'h', 4, 3);

INSERT INTO type_of_order (name) VALUES ('Quick' );
INSERT INTO type_of_order (name) VALUES ('Very far' );
INSERT INTO type_of_order (name) VALUES ('Ше якийсь');
INSERT INTO type_of_order (name) VALUES ('І ше якийсь');