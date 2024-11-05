create table cars (
	id serial primary key,
	brand text,
	model varchar(255),
	year_of_manufacture int
);

insert into cars(brand, model, year_of_manufacture)
values
	('Toyota', 'Camry', 2020),
	('Ford', 'Mustang', 2021),
	('Honda', 'Accord', 2019),
	('BMW', '3 Series', 2022),
	('Tesla', 'Model 3', 2021);

SELECT * from cars;

update cars set year_of_manufacture = 2005;

SELECT * from cars;

delete from cars; 

SELECT * from cars;