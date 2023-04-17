insert into person(fullname, year_of_birth) VALUES ('Иванов Иван Иванович', 1970);
insert into person(fullname, year_of_birth) VALUES ('Петров Петр Петрович', 1960);
insert into person(fullname, year_of_birth) VALUES ('Алексеев Алексей Алексеевич', 1978);
insert into person(fullname, year_of_birth) VALUES ('Точитский Павел Фарфаломеевич', 1982);
insert into person(fullname, year_of_birth) VALUES ('Жиглов Глеб Сергеевич', 1997);

insert into book(person_id, name, author, publication_date) VALUES (1, 'Над пропостью во ржи', 'Селинджер', 1951);
insert into book(person_id, name, author, publication_date) VALUES (1, '1984', 'Джордж Оруэлл', 2020);
insert into book(person_id, name, author, publication_date) VALUES (2, 'Джейн Эйр', 'Шарлотта Бронте', 2020);
insert into book(person_id, name, author, publication_date) VALUES (null, 'Скотный двор', 'Джордж Оруэлл', 1987);
