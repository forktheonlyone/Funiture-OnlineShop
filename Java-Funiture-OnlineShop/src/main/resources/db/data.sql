insert into user_tb (id, email, password, username, phone_number, address, roles) values (1, 'aaaaaa@gmail.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Colver Bradburn', '2285256418', '3 Valley Edge Point', 'ROLE_ADMIN');
insert into user_tb (id, email, password, username, phone_number, address, roles) values (2, 'malesi1@businessweek.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Manuel Alesi', '8669275722', '60 Cardinal Parkway', 'ROLE_USER');

insert into category (id, category_name, super_category_id) values (1, '목재 가구', null);
insert into category (id, category_name, super_category_id) values (2, '책상', 1);
insert into category (id, category_name, super_category_id) values (3, '유리 책상', 2);
insert into category (id, category_name, super_category_id) values (4, '참나무 책상', 2);
insert into category (id, category_name, super_category_id) values (5, '등나무 책상', 2);