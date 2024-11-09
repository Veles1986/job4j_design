CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

INSERT INTO departments (name) 
VALUES 
    ('Отдел продаж'),
    ('Отдел маркетинга'),
    ('Отдел разработки');

INSERT INTO employees (name, department_id)
VALUES 
    ('Алексей Иванов', 1),
    ('Мария Петрова', 1),
    ('Дмитрий Соколов', 1),
    ('Наталья Смирнова', 1),
    ('Иван Кузнецов', 1),
    ('Анна Попова', null),
    ('Петр Волков', 1),
    ('Ольга Козлова', 1),
    ('Максим Васильев', null),
    ('Татьяна Новикова', 1),
    ('Сергей Михайлов', null),
    ('Алена Захарова', 2),
    ('Владимир Николаев', 2),
    ('Екатерина Федорова', 2),
    ('Артур Павлов', 2),
    ('Олеся Богданова', null),
    ('Георгий Мартынов', 2),
    ('Светлана Воробьева', 2),
    ('Андрей Киселев', 2),
    ('Юлия Сидорова', 2),
    ('Илья Тихонов', 3),
    ('Оксана Беляева', null),
    ('Павел Орлов', 3),
    ('Валентина Гусева', 3),
    ('Константин Ершов', 3),
    ('Маргарита Фролова', 3),
    ('Егор Рябов', 3),
    ('Вера Никитина', 3),
    ('Роман Захаров', 3),
    ('Евгения Гончарова', null);

SELECT d.name, e.name
from departments as d left join employees as e on d.id = e.department_id;

SELECT d.name, e.name
from departments as d right join employees as e on d.id = e.department_id;

SELECT d.name, e.name
from departments as d full join employees as e on d.id = e.department_id;

SELECT d.name, e.name
from departments as d cross join employees as e;

SELECT d.name, e.name
from departments as d left join employees as e on d.id = e.department_id
where e.name is null;

SELECT d.name, e.name
from departments as d left join employees as e on d.id = e.department_id;

SELECT d.name, e.name
from employees as e right join departments as d on d.id = e.department_id;

create table teens (
    id serial primary key,
    name varchar(255),
    gender char(1)
);

INSERT INTO teens (name, gender) VALUES
    ('Вася', 'M'),
    ('Маша', 'F'),
    ('Саша', 'M'),
    ('Оля', 'F'),
    ('Петя', 'M'),
    ('Катя', 'F');

SELECT t1.name, t2.name
from teens as t1 cross join teens as t2
where t1.gender <> t2.gender and t1.id < t2.id;
