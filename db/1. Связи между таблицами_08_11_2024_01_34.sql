create table car_brands(
	brand_name varchar(255) primary key
);

create table car_models_first(
	model_name varchar(255) primary key,
	car_brands_brand varchar(255) references car_brands(brand_name)
);

create table car_codes(
	code int primary key
);

create table car_models_second(
	model_name varchar(255) primary key
);

create table car_models_and_codes(
	id serial primary key,
	model_name varchar(255) references car_models_second(model_name) unique,
	car_codes_code int references car_codes(code) unique
);

create table car_types(
	car_type varchar(255) primary key
);

create table car_models_third(
	model_name varchar(255) primary key
);

create table car_models_and_types(
	id serial primary key,
	car_types_car_type varchar(255) references car_types(car_type),
	car_models_model_nema varchar(255) references car_models_third(model_name)
);