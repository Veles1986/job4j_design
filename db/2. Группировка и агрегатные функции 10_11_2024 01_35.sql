create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO people (name) VALUES
    ('Alice'),
    ('Bob'),
    ('Charlie'),
    ('David'),
    ('Eve');

INSERT INTO devices (name, price) VALUES
    ('Laptop', 1499.99),
    ('Laptop', 1520.50),
    ('Laptop', 1505.75),
    
    ('Smartphone', 799.99),
    ('Smartphone', 820.00),
    ('Smartphone', 805.25),
    
    ('Tablet', 499.99),
    ('Tablet', 510.00),
    ('Tablet', 495.75),
    
    ('Smartwatch', 199.99),
    ('Smartwatch', 205.50),
    ('Smartwatch', 202.25),
    
    ('Headphones', 99.99),
    ('Headphones', 101.50),
    ('Headphones', 98.75);

INSERT INTO devices_people (device_id, people_id) VALUES
    (1, 1), (4, 1), (7, 1), (10, 1), (13, 1),
    (2, 2), (5, 2), (8, 2), (11, 2), (14, 2),
    (3, 3), (6, 3), (9, 3), (12, 3), (15, 3),
    (4, 4), (7, 4), (10, 4), (13, 4), (1, 4),
    (5, 5), (8, 5), (11, 5), (14, 5), (2, 5);

select dp.id ID, p.name Владелец, d.name Устройство, d.price Цена
from devices_people as dp join devices as d on dp.device_id = d.id join people as p on dp.people_id = p.id;

SELECT d.name Устройство, avg(d.price) as "Средняя стоимость"
from devices_people as dp join devices as d on dp.device_id = d.id
group by d.name;

SELECT p.name Устройство, avg(d.price) as "Средняя стоимость"
from devices_people as dp join devices as d on dp.device_id = d.id join people as p on dp.people_id = p.id
group by p.name;

SELECT p.name Устройство, avg(d.price) as "Средняя стоимость"
from devices_people as dp join devices as d on dp.device_id = d.id join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 620;
