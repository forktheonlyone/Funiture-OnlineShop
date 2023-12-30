insert into user_tb (id, email, password, username, phone_number, address, roles) values (1, 'aaaaaa@gmail.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Colver Bradburn', '2285256418', '3 Valley Edge Point', 'ROLE_ADMIN');
insert into user_tb (id, email, password, username, phone_number, address, roles) values (2, 'malesi1@businessweek.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Manuel Alesi', '8669275722', '60 Cardinal Parkway', 'ROLE_USER');

insert into category (id, category_name, super_category_id) values (1, '목재 가구', null);
insert into category (id, category_name, super_category_id) values (2, '책상', 1);
insert into category (id, category_name, super_category_id) values (3, '유리 책상', 2);
insert into category (id, category_name, super_category_id) values (4, '참나무 책상', 2);
insert into category (id, category_name, super_category_id) values (5, '등나무 책상', 2);

insert into product (id, delivery_fee, description, price, product_name, category_id) values (1, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상1', 3)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (2, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상2', 3)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (3, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상3', 3)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (4, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상4', 4)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (5, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상5', 4)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (6, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상6', 4)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (7, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상7', 5)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (8, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상8', 5)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (9, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상9', 5)

insert into option_tb (id, option_name, price, stock_quantity, product_id) values (1, '검정 책상', 1000, 60, 1)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (2, '하양 책상', 2000, 60, 1)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (3, '갈색 책상', 3000, 60, 1)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (4, '검정 책상', 1000, 60, 2)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (5, '하양 책상', 2000, 60, 2)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (6, '갈색 책상', 3000, 60, 2)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (7, '검정 책상', 1000, 60, 3)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (8, '하양 책상', 2000, 60, 3)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (9, '갈색 책상', 3000, 60, 3)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (10, '검정 책상', 1000, 60, 4)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (11, '하양 책상', 2000, 60, 4)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (12, '갈색 책상', 3000, 60, 4)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (13, '검정 책상', 1000, 60, 5)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (14, '하양 책상', 2000, 60, 5)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (15, '갈색 책상', 3000, 60, 5)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (16, '검정 책상', 1000, 60, 6)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (17, '하양 책상', 2000, 60, 6)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (18, '갈색 책상', 3000, 60, 6)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (19, '검정 책상', 1000, 60, 7)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (20, '하양 책상', 2000, 60, 7)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (21, '갈색 책상', 3000, 60, 7)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (22, '검정 책상', 1000, 60, 8)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (23, '하양 책상', 2000, 60, 8)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (24, '갈색 책상', 3000, 60, 8)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (25, '검정 책상', 1000, 60, 9)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (26, '하양 책상', 2000, 60, 9)
insert into option_tb (id, option_name, price, stock_quantity, product_id) values (27, '갈색 책상', 3000, 60, 9)

--1번 장바구니는 주문이 있으므로 삭제 불가
insert into cart_tb (id, price, quantity, option_id, user_id) values (1, 4000, 1, 3, 1)
insert into cart_tb (id, price, quantity, option_id, user_id) values (2, 8000, 2, 6, 1)
insert into cart_tb (id, price, quantity, option_id, user_id) values (3, 12000, 3, 9, 1)
insert into cart_tb (id, price, quantity, option_id, user_id) values (4, 12000, 3, 3, 2)
insert into cart_tb (id, price, quantity, option_id, user_id) values (5, 8000, 2, 6, 2)
insert into cart_tb (id, price, quantity, option_id, user_id) values (6, 4000, 1, 9, 2)

insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (1, 'pic1.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 1)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (2, 'pic2.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 1)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (3, 'pic3.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 1)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (4, 'pic4.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 2)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (5, 'pic5.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 2)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (6, 'pic6.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 2)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (7, 'pic7.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 3)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (8, 'pic8.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 4)
insert into product_file (id, file_name, file_path, file_size, file_type, uuid, product_id) values (9, 'pic9.png', 'C:/Users/NT767/OneDrive/바탕 화면/demodata/', 58636, '.png', 'asdf-asdf-asdf', 5)

insert into order_tb (id, order_date, user_id) values (1, '2023-12-27', 1)

insert into order_check (id, price, order_date, address, quantity, tid, option_id, user_id) values (1, 4000, 'asdfasdf', '2019-8-14 08:43:12', 1, 'asdfasfd', 3, 1)
insert into order_check (id, price, order_date, address, quantity, tid, option_id, user_id) values (2, 8000, 'asdfasdf', '2023-8-14 08:43:12', 2, 'asdfasfd', 6, 1)
insert into order_check (id, price, order_date, address, quantity, tid, option_id, user_id) values (3, 12000, 'asdfasdf', '2023-8-14 08:43:12', 3, 'asdfasfd', 9, 1)