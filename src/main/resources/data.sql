insert into hotel (id, name) values (1, 'orion');
insert into hotel (id, name) values (2, 'taj');

insert into department (id, name, hotel_id) values (1, 'food', 1);
insert into department (id, name, hotel_id) values (2, 'travel', 1);
insert into department (id, name, hotel_id) values (3, 'hospitality', 1)
insert into department (id, name, hotel_id) values (4, 'food', 1);

insert into touch_point(id, name,  department_id) values(1, 'dining',  1);
insert into touch_point(id, name,  department_id) values(2, 'kitchen', 1);
insert into touch_point(id, name,  department_id) values(3, 'parking', 2);
insert into touch_point(id, name,  department_id) values(4, 'reception', 3);

insert into user_status (id, status) values (1,'disable');
insert into user_status (id, status) values (2,'enable');

insert into user_type (id, type) values (1, 'ROLE_ADMIN');
insert into user_type (id, type) values (2, 'ROLE_MANAGER');
insert into user_type (id, type) values (3, 'ROLE_STAFF');

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values(1,   'manmay', 'secret',   'Manmay', 'Mohanty',               1,            1,         1);
insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values(2,  'mrunmay', 'secret',  'Mrunmay', 'Mohanty',               2,            1,         1);

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values(3,  'santosh', 'secret',  'Santosh', 'Kunatha',               2,                 3,         1);

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values(4,  'subhash', 'secret',  'Subhash', 'Goel',               2,               2,         1);

insert into user_detail_department (uid, did) values (1,1);
insert into user_detail_department (uid, did) values (1,2);
insert into user_detail_department (uid, did) values (1,3);
insert into user_detail_department (uid, did) values (2,4);
