CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES 
    ('John', 'Doe', 25, 'USA'),
    ('Jane', 'Smith', 32, 'Canada'),
    ('Ali', 'Khan', 19, 'UK'),
    ('Emily', 'Johnson', 27, 'USA'),
    ('Chris', 'Lee', 34, 'Canada'),
    ('Sophia', 'Taylor', 22, 'UK'),
    ('Liam', 'Brown', 28, 'USA'),
    ('Emma', 'White', 21, 'Canada'),
    ('Noah', 'Davis', 33, 'UK'),
    ('Olivia', 'Wilson', 17, 'USA'),
    ('William', 'Martinez', 23, 'Canada'),
    ('Isabella', 'Hernandez', 26, 'UK'),
    ('James', 'Lopez', 18, 'USA'),
    ('Ava', 'Gonzalez', 35, 'Canada'),
    ('Mia', 'Hall', 24, 'UK'),
    ('Benjamin', 'Allen', 29, 'USA'),
    ('Ethan', 'Young', 31, 'Canada'),
    ('Charlotte', 'King', 27, 'UK'),
    ('Logan', 'Scott', 20, 'USA'),
    ('Amelia', 'Green', 30, 'Canada'),
    ('Jacob', 'Adams', 22, 'UK'),
    ('Lucas', 'Baker', 33, 'USA'),
    ('Elijah', 'Nelson', 19, 'Canada'),
    ('Ella', 'Carter', 34, 'UK'),
    ('Alexander', 'Mitchell', 28, 'USA'),
    ('Aiden', 'Perez', 18, 'Canada'),
    ('Grace', 'Roberts', 24, 'UK'),
    ('Daniel', 'Turner', 29, 'USA'),
    ('Michael', 'Phillips', 31, 'Canada'),
    ('Abigail', 'Campbell', 30, 'UK'),
    ('Matthew', 'Parker', 25, 'USA'),
    ('Henry', 'Evans', 26, 'Canada'),
    ('Zoe', 'Edwards', 22, 'UK'),
    ('Hannah', 'Collins', 17, 'USA'),
    ('Jackson', 'Stewart', 35, 'Canada'),
    ('Lily', 'Sanchez', 19, 'UK'),
    ('Sebastian', 'Morris', 27, 'USA'),
    ('Victoria', 'Rogers', 24, 'Canada'),
    ('David', 'Reed', 28, 'UK'),
    ('Aria', 'Cook', 23, 'USA'),
    ('Owen', 'Morgan', 31, 'Canada'),
    ('Ella', 'Bell', 29, 'UK'),
    ('Nathan', 'Murphy', 20, 'USA'),
    ('Sofia', 'Bailey', 30, 'Canada'),
    ('Evelyn', 'Rivera', 17, 'UK'),
    ('Landon', 'Cooper', 18, 'USA'),
    ('Leah', 'Richardson', 19, 'Canada'),
    ('Dylan', 'Cox', 22, 'UK'),
    ('Natalie', 'Howard', 28, 'USA'),
    ('Gabriel', 'Ward', 24, 'Canada');

SELECT first_name, last_name, age, country
from customers
where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES
    (120, 1),
    (250, 2),
    (100, 3),
    (300, 4),
    (150, 5),
    (220, 6),
    (180, 7),
    (140, 8),
    (270, 9),
    (350, 10),
    (190, 11),
    (200, 12),
    (130, 13),
    (310, 14),
    (170, 15),
    (240, 16),
    (260, 17),
    (210, 18),
    (330, 19),
    (300, 20),
    (290, 21),
    (310, 22),
    (210, 23),
    (190, 24),
    (180, 25),
    (320, 26),
    (230, 27),
    (270, 28),
    (250, 29),
    (340, 30),
    (310, 31),
    (220, 32),
    (200, 33),
    (230, 34),
    (180, 35),
    (310, 36),
    (270, 37),
    (210, 38),
    (290, 39),
    (150, 40);

select first_name, last_name, age, country
from customers c
where 0 = (select count(o.customer_id) from orders o where o.customer_id = c.id);

select first_name, last_name, age, country
from customers c
where c.id not in (select o.id from orders o where o.customer_id = c.id);
