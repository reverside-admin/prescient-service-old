insert into user_status (id, status) values (1,'disable');
insert into user_status (id, status) values (2,'enable');

insert into user_type (id, type) values (1, 'admin');
insert into user_type (id, type) values (2, 'manager');
insert into user_type (id, type) values (3, 'staff');

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id)
                  values(1,   'manmay', 'secret',   'Manmay', 'Mohanty',               1,            1);
insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id)
                  values(2,  'mrunmay', 'secret',  'Mrunmay', 'Mohanty',               2,            1);

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id)
                  values(3,  'santosh', 'secret',  'santosh', 'KK',               2,                 3);

insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id)
                  values(4,  'subhash', 'secret',  'subhash', 'goel',               1,               2);


