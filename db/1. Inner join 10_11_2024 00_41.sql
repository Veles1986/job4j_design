create table brands(
  id serial primary key,
  name varchar(255)
);

create table models(
  id serial primary key,
  name varchar(255),
  year_of_first_release int,
  year_of_last_release int,
  brands_id int references brands(id)
);

insert into brands(name) values
  ('BMW'),
  ('Toyota'),
  ('Ford');

insert into models(name, year_of_first_release, year_of_last_release, brands_id) values
  ('BMW 3 Series', 1975, 2024, 1),
  ('BMW 5 Series', 1972, 2024, 1),
  ('BMW X5', 1999, 2024, 1),
  ('Toyota Corolla', 1966, 2024, 2),
  ('Toyota Camry', 1982, 2024, 2),
  ('Toyota Land Cruiser', 1951, 2024, 2),
  ('Ford Mustang', 1964, 2024, 3),
  ('Ford F-150', 1948, 2024, 3),
  ('Ford Fiesta', 1976, 2023, 3),
  ('Audi A4', 1994, 2024, null),
  ('Honda Civic', 1972, 2024, null),
  ('Mercedes-Benz S-Class', 1972, 2024, null),
  ('Nissan Skyline', 1957, 2024, null),
  ('Chevrolet Impala', 1958, 2020, null);


select b.name Бренд, m.name Модель, m.year_of_first_release as "Год выпуска"
from models as m join brands as b on m.brands_id = b.id;

select b.name Бренд, m.name Модель
from models as m join brands as b on m.brands_id = b.id where b.name != 'Toyota';

select m.brands_id Бренд, m.name as "Модель авто"
from models as m where m.brands_id is null;
