create table car_bodies(
  id serial primary key,
  name varchar(255)
);

create table car_engines(
  id serial primary key,
  name varchar(255)
);

create table car_transmissions(
  id serial primary key,
  name varchar(255)
);

create table cars(
  id serial primary key,
  name varchar(255),
  body_id int references car_bodies(id),
  engine_id int references car_engines(id),
  transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies (name)
VALUES 
    ('Седан'),
    ('Внедорожник'),
    ('Хэтчбек'),
  ('Купе');

INSERT INTO car_engines (name)
VALUES 
    ('1.5 л Бензиновый'),
    ('2.0 л Бензиновый'),
    ('1.8 л Дизельный'),
    ('3.0 л Дизельный'),
  ('Электрический'),
  ('2.5 л Дизельный');

INSERT INTO car_transmissions (name)
VALUES 
    ('Механическая'),
    ('Автоматическая'),
    ('Роботизированная'),
    ('Вариатор');

INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES 
    ('Toyota Camry', 1, 2, 2),         -- Седан, 2.0 л Бензиновый, Автоматическая
    ('Ford Focus', 1, 1, 1),           -- Седан, 1.5 л Бензиновый, Механическая
    ('Honda Accord', 1, 2, 3),         -- Седан, 2.0 л Бензиновый, Роботизированная
    ('BMW X5', 2, 4, 2),               -- Внедорожник, 3.0 л Дизельный, Автоматическая
    ('Nissan Leaf', 1, 5, 4),          -- Седан, Электрический, Вариатор
    ('Audi Q7', 2, 4, 2),              -- Внедорожник, 3.0 л Дизельный, Автоматическая
    ('Mazda CX-5', 2, 1, 2),           -- Внедорожник, 1.5 л Бензиновый, Автоматическая
    ('Volkswagen Golf', 3, 1, 1),      -- Хэтчбек, 1.5 л Бензиновый, Механическая
    ('Kia Soul', 3, 2, null),             -- Хэтчбек, 2.0 л Бензиновый, Вариатор
    ('Chevrolet Bolt', 3, 5, 2),       -- Хэтчбек, Электрический, Автоматическая
    ('Hyundai Tucson', 2, 3, 2),       -- Внедорожник, 1.8 л Дизельный, Автоматическая
    ('Tesla Model S', null, 5, 3),        -- Седан, Электрический, Роботизированная
    ('Ford Escape', 2, 2, 2),          -- Внедорожник, 2.0 л Бензиновый, Автоматическая
    ('Renault Duster', null, 3, 1),       -- Внедорожник, 1.8 л Дизельный, Механическая
    ('Toyota Corolla', 1, 1, 1),       -- Седан, 1.5 л Бензиновый, Механическая
    ('Honda Civic', 3, 1, 4),          -- Хэтчбек, 1.5 л Бензиновый, Вариатор
    ('Mercedes-Benz GLE', 2, 4, 2),    -- Внедорожник, 3.0 л Дизельный, Автоматическая
    ('Subaru Forester', 2, 2, 4),      -- Внедорожник, 2.0 л Бензиновый, Вариатор
    ('Mitsubishi Outlander', 2, 1, 2), -- Внедорожник, 1.5 л Бензиновый, Автоматическая
    ('Hyundai Kona Electric', 3, null, 3),-- Хэтчбек, Электрический, Роботизированная
    ('Peugeot 3008', 2, 2, 3),         -- Внедорожник, 2.0 л Бензиновый, Роботизированная
    ('Volkswagen Tiguan', 2, 2, 2),    -- Внедорожник, 2.0 л Бензиновый, Автоматическая
    ('Fiat 500', 3, 1, 1),             -- Хэтчбек, 1.5 л Бензиновый, Механическая
    ('Skoda Octavia', 1, 1, 3),        -- Седан, 1.5 л Бензиновый, Роботизированная
    ('BMW 3 Series', 1, null, 2),         -- Седан, 1.8 л Дизельный, Автоматическая
    ('Audi A4', 1, 2, 2),              -- Седан, 2.0 л Бензиновый, Автоматическая
    ('Toyota RAV4', 2, 1, 4),          -- Внедорожник, 1.5 л Бензиновый, Вариатор
    ('Chevrolet Tahoe', 2, 4, 2),      -- Внедорожник, 3.0 л Дизельный, Автоматическая
    ('Mini Cooper', 3, 1, 3),          -- Хэтчбек, 1.5 л Бензиновый, Роботизированная
    ('Lexus RX', 2, 3, null);             -- Внедорожник, 1.8 л Дизельный, Автоматическая

select c.id, c.name Модель, b.name as "Тип кузова", e.name Двигатель, t.name Трансмиссия
from cars as c 
full join car_bodies as b on c.body_id = b.id
full join car_engines as e on c.engine_id = e.id 
full join car_transmissions as t on c.transmission_id = t.id
where c.name is not null
order by b.name, c.name;

select b.name as "Тип кузова"
from cars as c 
full join car_bodies as b on c.body_id = b.id
where c.name is null
order by b.name;

select e.name as "Двигатель"
from cars as c 
full join car_engines as e on c.engine_id = e.id
where c.name is null
order by e.name;

select t.name as "Трансмиссия"
from cars as c 
full join car_transmissions as t on c.transmission_id = t.id
where c.name is null
order by t.name;