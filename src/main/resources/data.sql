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


insert into itcs_tag_read (id, guest_card, zone) values (1, 1000, 1);
insert into itcs_tag_read (id, guest_card, zone) values (2, 1000, 1);
insert into itcs_tag_read (id, guest_card, zone) values (3, 1000, 1);
insert into itcs_tag_read (id, guest_card, zone) values (4, 1001, 1);
insert into itcs_tag_read (id, guest_card, zone) values (5, 1002, 1);

insert into itcs_tag_read (id, guest_card, zone) values (6, 1003, 2);
insert into itcs_tag_read (id, guest_card, zone) values (7, 1004, 2);
insert into itcs_tag_read (id, guest_card, zone) values (8, 1005, 3);
/*insert into itcs_tag_read (id, guest_card, zone) values (9, 1006, 4);*/


insert into guest_profile_detail (id, hotel_id, first_name) values (100, 1, 'guest1');
insert into guest_profile_detail (id, hotel_id, first_name) values (101, 1, 'guest2');
insert into guest_profile_detail (id, hotel_id, first_name) values (102, 1, 'guest3');

insert into guest_profile_detail (id, hotel_id, first_name) values (103, 1, 'guest4');
insert into guest_profile_detail (id, hotel_id, first_name) values (104, 1, 'guest5');
insert into guest_profile_detail (id, hotel_id, first_name) values (105, 1, 'guest6');
/*insert into guest_profile_detail (id, hotel_id, first_name) values (106, 1, 'guest7');*/

insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (1, 1000, 100);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (2, 1001, 101);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (3, 1002, 102);

insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (4, 1003, 103);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (5, 1004, 104);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (6, 1005, 105);
/*insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (7, 1006, 106);*/


insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (1, '999', 100, '2010-04-01 00:00:00', '2010-04-05 00:00:00', 100);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (2, '888', 101, '2010-04-02 00:00:00', '2010-04-06 00:00:00', 101);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (3, '777', 102, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 102);

insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (4, '666', 103, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 103);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (5, '555', 104, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 104);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (6, '444', 105, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 105);
/*insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (7, '333', 106, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 106);*/


insert into guest_card (id, mag_stripe_no) values (1, 'mag1');
insert into guest_card (id, mag_stripe_no) values (2, 'mag2');
insert into guest_card (id, mag_stripe_no) values (3, 'mag3');
insert into guest_card (id, mag_stripe_no) values (4, 'mag4');
insert into guest_card (id, mag_stripe_no) values (5, 'mag5');



