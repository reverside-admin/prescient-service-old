insert into hotel (id, name) values (1, 'orion');
insert into hotel (id, name) values (2, 'taj');

insert into department (id, name, hotel_id) values (1, 'food', 1);
insert into department (id, name, hotel_id) values (2, 'travel', 1);
insert into department (id, name, hotel_id) values (3, 'hospitality', 1)
insert into department (id, name, hotel_id) values (4, 'food', 2);

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
                  values( 1,  'manmay', 'secret',   'Manmay', 'Mohanty',               1,            1,        1);
insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values( 2, 'mrunmay', 'secret',  'Mrunmay', 'Mohanty',               2,            1,        1);
insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values( 3, 'santosh', 'secret',  'Santosh', 'Kunatha',               2,           3,         1);
insert into  user_detail(id, user_name, password, first_name, last_name, user_status_id , user_type_id, hotel_id)
                  values( 4, 'subhash', 'secret',  'Subhash',    'Goel',               2,            2,        1);

insert into user_detail_departments (uid, did) values (1,1);
insert into user_detail_departments (uid, did) values (1,2);
insert into user_detail_departments (uid, did) values (1,3);
insert into user_detail_departments (uid, did) values (2,1);
insert into user_detail_departments (uid, did) values (2,2);

insert into user_detail_touch_points (uid, tid) values (1, 1);
insert into user_detail_touch_points (uid, tid) values (1, 3);
insert into user_detail_touch_points (uid, tid) values (2, 3);


insert into guest_profile_detail (id, hotel_id, first_name) values (1, 1, 'chinmay');
insert into guest_profile_detail (id, hotel_id, first_name) values (2, 1, 'braj');
insert into guest_profile_detail (id, hotel_id, first_name) values (3, 1, 'surya');
insert into guest_profile_detail (id, hotel_id, first_name) values (4, 1, 'twinkle');
insert into guest_profile_detail (id, hotel_id, first_name) values (5, 1, 'deb');


insert into guest_card (id, hotel_key, rfid_key) values (1, 101, 1001);
insert into guest_card (id, hotel_key, rfid_key) values (2, 102, 1002);
insert into guest_card (id, hotel_key, rfid_key) values (3, 103, 1003);
insert into guest_card (id, hotel_key, rfid_key) values (4, 104, 1004);
insert into guest_card (id, hotel_key, rfid_key) values (5, 105, 1005);

insert into itcs_tag_read (id, guest_card, zone) values (1, 11, 1);
insert into itcs_tag_read (id, guest_card, zone) values (2, 11, 1);
insert into itcs_tag_read (id, guest_card, zone) values (3, 12, 1);
insert into itcs_tag_read (id, guest_card, zone) values (4, 12, 1);
insert into itcs_tag_read (id, guest_card, zone) values (5, 13, 1);

insert into itcs_tag_read (id, guest_card, zone) values (6, 21, 2);
insert into itcs_tag_read (id, guest_card, zone) values (7, 21, 2);
insert into itcs_tag_read (id, guest_card, zone) values (8, 21, 2);
insert into itcs_tag_read (id, guest_card, zone) values (9, 22, 2);
insert into itcs_tag_read (id, guest_card, zone) values (10, 22, 2);

insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (1, 11, 1);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (2, 12, 2);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (3, 13, 3);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (4, 21, 4);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (5, 22, 5);
