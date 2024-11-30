-- Создание таблицы company
CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Создание таблицы person
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    company_id INTEGER NOT NULL REFERENCES company(id)
);

-- Заполнение таблицы company
INSERT INTO company (name) VALUES
('TechCorp'),
('Innovatech'),
('FutureWorks'),
('DataGen'),
('NextGen Solutions'),
('SkyNet Industries');

-- Заполнение таблицы person
INSERT INTO person (name, company_id) VALUES
-- Работники компании 1 (15 работников)
('Alice Johnson', 1),
('Bob Smith', 1),
('Carol Williams', 1),
('David Brown', 1),
('Emma Davis', 1),
('Frank Miller', 1),
('Grace Wilson', 1),
('Hank Moore', 1),
('Ivy Taylor', 1),
('Jack Anderson', 1),
('Kathy Thomas', 1),
('Liam Jackson', 1),
('Mia White', 1),
('Noah Harris', 1),
('Olivia Martin', 1),

-- Работники компании 2 (19 работников)
('Paul Clark', 2),
('Quinn Lewis', 2),
('Rachel Walker', 2),
('Steve Hall', 2),
('Tina Allen', 2),
('Uma King', 2),
('Victor Scott', 2),
('Wendy Green', 2),
('Xander Adams', 2),
('Yvonne Baker', 2),
('Zack Hill', 2),
('Amy Lee', 2),
('Brian Wright', 2),
('Cindy Lopez', 2),
('Derek Edwards', 2),
('Ella Cruz', 2),
('Fred Nelson', 2),
('Gina Reed', 2),
('Harry Campbell', 2),

-- Работники компании 3 (21 работник)
('Isla Perez', 3),
('Jake Roberts', 3),
('Karen Turner', 3),
('Leo Phillips', 3),
('Megan Parker', 3),
('Nate Evans', 3),
('Owen Collins', 3),
('Paula Stewart', 3),
('Quentin Sanchez', 3),
('Rita Morris', 3),
('Sam Peterson', 3),
('Tara Howard', 3),
('Uma Ward', 3),
('Vince Bell', 3),
('Willa Cooper', 3),
('Xena Flores', 3),
('Yuri Butler', 3),
('Zane Foster', 3),
('Anna Simmons', 3),
('Bill Kelly', 3),
('Cathy Price', 3),

-- Работники компании 4 (13 работников)
('Dylan Brooks', 4),
('Eva Sanders', 4),
('Finn Powell', 4),
('Gwen Jenkins', 4),
('Holly Perry', 4),
('Ian Bennett', 4),
('Julia Barnes', 4),
('Kyle Hughes', 4),
('Lila Rivera', 4),
('Mason Henderson', 4),
('Nina Ross', 4),
('Omar Richards', 4),
('Piper Torres', 4),

-- Работники компании 5 (21 работник)
('Quinn Murphy', 5),
('Rachel Gray', 5),
('Sean Ramirez', 5),
('Tina James', 5),
('Uma Watson', 5),
('Victor Brooks', 5),
('Wanda Griffin', 5),
('Xavier Diaz', 5),
('Yasmine Bennett', 5),
('Zachary Rivera', 5),
('Avery Cox', 5),
('Bailey Howard', 5),
('Cameron Ward', 5),
('Delilah Ford', 5),
('Ethan Spencer', 5),
('Fiona Hamilton', 5),
('Gavin Watts', 5),
('Hazel Coleman', 5),
('Isaac Young', 5),
('Jasmine Kelly', 5),
('Kai Peterson', 5),

-- Работники компании 6 (20 работников)
('Liam Price', 6),
('Mia Cruz', 6),
('Noah Simmons', 6),
('Olivia Foster', 6),
('Peyton Baker', 6),
('Quincy Bell', 6),
('Riley Adams', 6),
('Skylar Butler', 6),
('Taylor Lopez', 6),
('Ulysses Flores', 6),
('Vera Hill', 6),
('Wyatt Cooper', 6),
('Xander Morris', 6),
('Yara Ward', 6),
('Zeke Parker', 6),
('Aubrey Nelson', 6),
('Blake Phillips', 6),
('Carson Perry', 6),
('Daphne Rivera', 6),
('Evan Bennett', 6);

select p.name, c.name
from person as p right join company as c on c.id = p.company_id
where c.id <> 5;

SELECT c.name, COUNT(p.id) AS employee_count
FROM company c
JOIN person p ON c.id = p.company_id
GROUP BY c.id, c.name
HAVING COUNT(p.id) = (
    SELECT MAX(employee_count)
    FROM (
        SELECT COUNT(p.id) AS employee_count
        FROM person p
        GROUP BY p.company_id
    ) AS subquery
);
