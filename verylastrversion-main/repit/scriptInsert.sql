insert into authentication values ('Pasha', '123456', 'ROLE_TUTOR',3);
insert into authentication values ('Artem@mail.ru', '1', 'ROLE_STUDENT',4);
insert into authentication values ('Kirill', '123456', 'ROLE_STUDENT',10);

insert into tutor values (3, 'Gresko Pavel Nikolaevich', '+79397111503', 'Samara', 5, 'Samara University');

insert into student values (10, 'Simovin Kirill Konstantinovich', 20, 'Samara', '+79397111513');
insert into student values (4, 'Ivanov Artem Alekseevich', 21, 'Samara', '+79397111514');

insert into advertisement values(1, 3, 1000, '1:30:00', 'Математика');

insert into lesson values(1, 1, 4, 'yes', '2023-03-29 17:00', '2023-03-29 18:30', 5, null);
