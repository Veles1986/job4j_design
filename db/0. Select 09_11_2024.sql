create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date) VALUES
    ('Лев', 15000, '1890-01-01'),
    ('Слон', 21000, NULL),
    ('Тигр', 17000, '1900-05-20'),
    ('Жираф', 16000, NULL),
    ('Панда', 14000, '1985-11-26'),
    ('Зебра', 13000, '1920-06-15'),
    ('Кенгуру', 12500, NULL),
    ('Медведь', 19500, '1955-08-12'),
    ('Лама', 10500, NULL),
    ('Койот', 11800, '1962-04-19'),
    ('Лиса', 13500, NULL),
    ('Волк', 14000, '1915-03-25'),
    ('Орел', 11000, NULL),
    ('Сокол', 17500, '1937-07-12'),
    ('Фламинго', 10000, NULL),
    ('Пеликан', 20000, '1945-10-05'),
    ('Черепаха', 21000, NULL),
    ('Крокодил', 20000, '1880-02-20'),
    ('Анаконда', 17000, NULL),
    ('Питон', 15000, '1930-03-15'),
    ('Верблюд', 12000, NULL),
    ('Буйвол', 16000, '1940-12-11'),
    ('Леопард', 12500, NULL),
    ('Ягуар', 14500, '1950-05-06'),
    ('Пума', 13000, NULL),
    ('Косуля', 13500, '1968-07-08'),
    ('Лось', 14000, NULL),
    ('Олень', 10500, '1912-10-03'),
    ('Барсук', 11500, NULL),
    ('Енот', 11800, '1939-06-15'),
    ('Куница', 13000, NULL),
    ('Хорек', 12500, '1902-02-19'),
    ('Шакал', 12000, NULL),
    ('Манул', 13000, '1957-01-01'),
    ('Песец', 14500, NULL),
    ('Рысь', 10500, '1964-11-30'),
    ('Ласка', 11500, NULL),
    ('Кролик', 10000, '1929-08-16'),
    ('Сурок', 12500, NULL),
    ('Бобр', 14000, '1982-07-17'),
    ('Выдра', 12500, NULL),
    ('Медоед', 15000, '1918-09-25'),
    ('Шиншилла', 16000, NULL),
    ('Летучая мышь', 17000, '1975-03-13'),
    ('Оцелот', 15000, NULL),
    ('Мангуст', 14000, '1890-07-29'),
    ('Коала', 12500, NULL),
    ('Вомбат', 13500, '1948-12-12');

SELECT * from fauna where name like '%рыба%' or name like 'рыба%' or name like 'рыба%';

SELECT * from fauna where avg_age >= 15000 and avg_age <= 18000;

SELECT * from fauna where discovery_date is null;

SELECT * from fauna where discovery_date < '01.01.1950';

