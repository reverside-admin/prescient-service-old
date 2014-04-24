insert into hotel (id, name) values (1, 'Orion');
insert into hotel (id, name) values (2, 'Taj');

insert into department (id, name, hotel_id) values (1, 'Food', 1);
insert into department (id, name, hotel_id) values (2, 'Travel', 1);
insert into department (id, name, hotel_id) values (3, 'Hospitality', 1)
insert into department (id, name, hotel_id) values (4, 'Food', 2);

insert into touch_point(id, name,  department_id) values(1, 'Dining',  1);
insert into touch_point(id, name,  department_id) values(2, 'Kitchen', 1);
insert into touch_point(id, name,  department_id) values(3, 'Parking', 2);
insert into touch_point(id, name,  department_id) values(4, 'Reception', 3);

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

insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (100, 1, 'John', '1988-04-21 00:00:00', 'Male', 'Indian', 'AZ74539', 'Joe', 'Smith', 'Mr');
insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (101, 1, 'Jasmine', '1978-12-3 00:00:00', 'Female', 'SouthAfrican', 'AZ74539', 'Jas', 'Roy', 'Miss');
insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (102, 1, 'John', '1989-01-22 00:00:00', 'Male', 'American', 'JH74535', 'John', 'Karthy', 'Mr');

insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (103, 1, 'Robert', '1999-04-1 00:00:00', 'Male', 'Indian', 'QP74536', 'Rob', 'Alvis', 'Mr');
insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (104, 1, 'Pooja', '1967-08-9 00:00:00', 'Female', 'Indian', 'XL74623', 'Poo', 'Das', 'Miss');
insert into guest_profile_detail (id, hotel_id, first_name, dob, gender, nationality_id, passport_number, preferred_name, surname, title)
values (105, 1, 'Shreya', '1988-07-06 00:00:00', 'Female', 'SouthAfrican', 'KQ74959', 'sree', 'Bata', 'Miss');
/*insert into guest_profile_detail (id, hotel_id, first_name) values (106, 1, 'guest7');*/

insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (1, 1000, 100);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (2, 1001, 101);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (3, 1002, 102);

insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (4, 1003, 103);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (5, 1004, 104);
insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (6, 1005, 105);
/*insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (7, 1006, 106);*/


insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind, guest_profile_detail_id, hotel_id) values (1, '999', 100, '2010-04-01 00:00:00', '2010-04-05 00:00:00', true, 100, 1);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind, guest_profile_detail_id, hotel_id) values (2, '888', 101, '2010-04-02 00:00:00', '2010-04-06 00:00:00', false, 101, 1);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind, guest_profile_detail_id, hotel_id) values (3, '777', 102, '2010-04-03 00:00:00', '2010-04-07 00:00:00', false, 102, 1);

insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind,  guest_profile_detail_id, hotel_id) values (4, '666', 103, '2010-04-03 00:00:00', '2010-04-07 00:00:00', false , 103, 1);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind,  guest_profile_detail_id, hotel_id) values (5, '555', 104, '2010-04-03 00:00:00', '2010-04-07 00:00:00', false , 104, 1);
insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, current_stay_ind,  guest_profile_detail_id, hotel_id) values (6, '444', 105, '2010-04-03 00:00:00', '2010-04-07 00:00:00', false, 105, 1);
/*insert into guest_stay_detail (id, room_id, guest_id, arrival_time, departure_time, guest_profile_detail_id) values (7, '333', 106, '2010-04-03 00:00:00', '2010-04-07 00:00:00', 106);*/


insert into guest_card (id, mag_stripe_no) values (1, 'Room-001');
insert into guest_card (id, mag_stripe_no) values (2, 'Room-002');
insert into guest_card (id, mag_stripe_no) values (3, 'Room-003');
insert into guest_card (id, mag_stripe_no) values (4, 'Room-004');
insert into guest_card (id, mag_stripe_no) values (5, 'Room-005');



