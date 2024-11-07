create table Roles(
	id serial primary key,
	role text
);

create table Rules(
	id serial primary key,
	rule text
);

create table Users(
	id serial primary key,
	username text,
	role_id int references Roles(id)
);

create table RolesAndRules(
	id serial primary key,
	role_id int references Roles(id),
	rule_id int references Rules(id)
);

create table Categories(
	id serial primary key,
	categorie text
);

create table States(
	id serial primary key,
	state text
);

create table Items(
	id serial primary key,
	itemText text,
	categories_id int references Categories(id),
	states_id int references States(id)
);


create table Comments(
	id serial primary key,
	commentText text,
	item_id int references Items(id)
);

create table Attachs(
	id serial primary key,
	filePath text,
	item_id int references Items(id)
);

INSERT into Categories(categorie) values
	('Важное'),
	('Неважное');

INSERT into States(state) values
	('Рассмотрено'),
	('Не рассмотрено');

INSERT into Roles(role) values
	('Пользователь'),
	('Программист');

INSERT into Rules(rule) values
	('Чтение'),
	('Запись'),
	('Чтение и запись');

INSERT into RolesAndRules(role_id, rule_id) values
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2),
	(2, 3);

INSERT into Users(username, role_id) values
	('user1', 1),
	('user2', 1),
	('user3', 2);

INSERT into Items(itemText, categories_id, states_id) values
	('Помогите !!!', 1, 1),
	('Помогите !!!', 2, 1),
	('Помогите !!!', 1, 2),
	('Помогите !!!', 2, 2);

INSERT into Comments(commentText, item_id) values
	('Комментарий 1', 1),
	('Комментарий 2', 1),
	('Комментарий 3', 2),
	('Комментарий 4', 2),
	('Комментарий 5', 3),
	('Комментарий 6', 3),
	('Комментарий 7', 4),
	('Комментарий 8', 4);

INSERT into Attachs(filePath, item_id) values
	('Путь 1', 1),
	('Путь 2', 2),
	('Путь 3', 3),
	('Путь 4', 4);