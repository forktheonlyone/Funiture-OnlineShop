insert into user_tb (id, email, password, username, phone_number, address, roles) values (1, 'aaaaaa@gmail.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Colver Bradburn', '2285256418', '3 Valley Edge Point', 'ROLE_ADMIN');
insert into user_tb (id, email, password, username, phone_number, address, roles) values (2, 'malesi1@businessweek.com', '{bcrypt}$2a$10$x1V7huFWMKlKmtKYV0rLhO.KxEBzVcMRejjjfvv293XMsstztDcMG', 'Manuel Alesi', '8669275722', '60 Cardinal Parkway', 'ROLE_USER');

INSERT INTO category (id, category_name, super_category_id) VALUES (1, '가구', NULL);
INSERT INTO category (id, category_name, super_category_id) VALUES (2, '조명', NULL);
INSERT INTO category (id, category_name, super_category_id) VALUES (3, '인테리어 장식', NULL);
INSERT INTO category (id, category_name, super_category_id) VALUES (4, '침대', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (5, '소파', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (6, '테이블', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (7, '의자', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (8, '서랍장', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (9, '식탁', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (10, '수납 가구', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (11, '전등', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (12, '스탠드', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (13, '조명 소품', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (14, '실내 조명', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (15, '실외 조명', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (16, '테이블 램프', 2);
INSERT INTO category (id, category_name, super_category_id) VALUES (17, '시계', 3);
INSERT INTO category (id, category_name, super_category_id) VALUES (18, '화분', 3);
INSERT INTO category (id, category_name, super_category_id) VALUES (19, '벽걸이 장식', 3);
INSERT INTO category (id, category_name, super_category_id) VALUES (20, '장식용 쿠션', 3);
INSERT INTO category (id, category_name, super_category_id) VALUES (21, '책장', 1);
INSERT INTO category (id, category_name, super_category_id) VALUES (22, '장식용 액자', 3);
INSERT INTO category (id, category_name, super_category_id) VALUES (23, '5단 서랍장', 8);
INSERT INTO category (id, category_name, super_category_id) VALUES (24, '3단 서랍장', 8);
INSERT INTO category (id, category_name, super_category_id) VALUES (25, '6단 서랍장', 8);
INSERT INTO category (id, category_name, super_category_id) VALUES (26, '원형 식탁', 9);
INSERT INTO category (id, category_name, super_category_id) VALUES (27, '사각 식탁', 9);
INSERT INTO category (id, category_name, super_category_id) VALUES (28, '확장형 식탁', 9);
INSERT INTO category (id, category_name, super_category_id) VALUES (29, '서랍형 수납장', 10);
INSERT INTO category (id, category_name, super_category_id) VALUES (30, '서랍장 형 수납장', 10);
INSERT INTO category (id, category_name, super_category_id) VALUES (31, '선반형 수납장', 10);
INSERT INTO category (id, category_name, super_category_id) VALUES (32, '천장 조명', 14);
INSERT INTO category (id, category_name, super_category_id) VALUES (33, '벽 조명', 14);
INSERT INTO category (id, category_name, super_category_id) VALUES (34, '스탠드 조명', 14);
INSERT INTO category (id, category_name, super_category_id) VALUES (35, '정원 조명', 15);
INSERT INTO category (id, category_name, super_category_id) VALUES (36, '벽면 조명', 15);
INSERT INTO category (id, category_name, super_category_id) VALUES (37, '경로 조명', 15);
INSERT INTO category (id, category_name, super_category_id) VALUES (38, '프레임 액자', 19);
INSERT INTO category (id, category_name, super_category_id) VALUES (39, '월페이퍼', 19);
INSERT INTO category (id, category_name, super_category_id) VALUES (40, '벽 스티커', 19);
INSERT INTO category (id, category_name, super_category_id) VALUES (41, '솜털 쿠션', 20);
INSERT INTO category (id, category_name, super_category_id) VALUES (42, '포근 쿠션', 20);
INSERT INTO category (id, category_name, super_category_id) VALUES (43, '도자기 쿠션', 20);
INSERT INTO category (id, category_name, super_category_id) VALUES (44, '일반 책상', 21);
INSERT INTO category (id, category_name, super_category_id) VALUES (45, '전면 책상', 21);
INSERT INTO category (id, category_name, super_category_id) VALUES (46, '독서실 책상', 21);
INSERT INTO category (id, category_name, super_category_id) VALUES (47, '목재 액자', 22);
INSERT INTO category (id, category_name, super_category_id) VALUES (48, '금속 액자', 22);
INSERT INTO category (id, category_name, super_category_id) VALUES (49, '유리 액자', 22);


insert into product (id, delivery_fee, description, price, product_name, category_id) values (1, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상1', 44)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (2, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상2', 44)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (3, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상3', 44)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (4, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상4', 45)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (5, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상5', 45)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (6, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상6', 45)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (7, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상7', 46)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (8, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상8', 46)
insert into product (id, delivery_fee, description, price, product_name, category_id) values (9, 500, '이거슨 레스비 책상이여', 1000, '레쓰비 책상9', 46)

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

insert into order_check (id, price, order_date, order_id, quantity, tid, option_id, user_id) values (1, 4000, '2019-8-14 08:43:12', 'asdfasdf', 1, 'asdfasfd', 3, 1)
insert into order_check (id, price, order_date, order_id, quantity, tid, option_id, user_id) values (2, 8000, '2023-8-14 08:43:12', 'asdfasdf', 2, 'asdfasfd', 6, 1)
insert into order_check (id, price, order_date, order_id, quantity, tid, option_id, user_id) values (3, 12000,'2023-8-14 08:43:12', 'asdfasdf', 3, 'asdfasfd', 9, 1)