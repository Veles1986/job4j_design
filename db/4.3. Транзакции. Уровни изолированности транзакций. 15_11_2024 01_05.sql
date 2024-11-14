CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary FLOAT NOT NULL
);

INSERT INTO employees (name, position, salary) VALUES 
('ivan ivanov', 'Manager', 50000.00),
('Petr Petrov', 'Programmer', 80000.00),
('Svetlana Smirnova', 'Analytic', 75000.00);