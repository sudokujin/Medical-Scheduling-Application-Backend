-- STEP 3
BEGIN TRANSACTION;

-- user/admin
INSERT INTO users (username,password_hash,role)
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role)
VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-------------------------------------------------------------------------------------------------------------------
-- users/doctor RUN THIS FIRST
INSERT INTO users (username,password_hash,role)
VALUES ('qing','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

INSERT INTO users (username,password_hash,role)
VALUES ('Jordan','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

INSERT INTO users (username,password_hash,role)
VALUES ('Salaj','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

INSERT INTO users (username,password_hash,role)
VALUES ('DomTheFrontEndPro','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

INSERT INTO users (username,password_hash,role)
VALUES ('YoungjinLovesCake','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

-- doctors CHANGE USER_ID ACCODRINGLY
INSERT INTO doctor
(user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number)
VALUES(3, 'Qing','Jin','Dermontology',3, 3000, '34792347');

INSERT INTO doctor
(user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number)
VALUES(4, 'Jordan','Bruntz','Bones',4, 4000, '234683264283');

INSERT INTO doctor
(user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number)
VALUES(5, 'Salaj','Choudhary','Brain doctor', 6, 8000, '73737262');

INSERT INTO doctor
(user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number)
VALUES(6, 'Dom','Lorenz','Heart Surgeon', 5, 6000, '333333');

INSERT INTO doctor
(user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number)
VALUES(7, 'Youngjin','Kwon','Psychologist', 7, 9000, '384834756834');
-------------------------------------------------------------------------------------------------------

-- patient/user RUN THIS FIRST
INSERT INTO users (username,password_hash,role)
VALUES ('Anthony','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role)
VALUES ('Max','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role)
VALUES ('Jordan Junior', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role)
VALUES ('Little Qing', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO users (username,password_hash,role)
VALUES ('David', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

-- patients CHANGE USER_ID ACCODRINGLY

INSERT INTO patient(
	user_id, first_name, last_name, address, city, states, zipcode, email_address, patient_number, birthdate)
	VALUES (8, 'Anthony', 'Orlando', 'Anthony Land', 'Philly', 'Japan', '33333', 'ann@gmail', '3333333000', '2023/09/12');

INSERT INTO patient(
	user_id, first_name, last_name, address, city, states, zipcode, email_address, patient_number, birthdate)
	VALUES (9, 'Max', 'Orlando', 'Max Land', 'Tokyo', 'Japan', '33333', 'pan@gmail', '3333333000', '2023/09/09');
INSERT INTO patient(
	user_id, first_name, last_name, address, city, states, zipcode, email_address, patient_number, birthdate)
	VALUES (10, 'Jordan', 'Orlando', 'Jordan Land', 'Tokyo', 'Japan', '3333999', 'pan@gmail', '3333333000', '2023/09/08');

INSERT INTO patient(
	user_id, first_name, last_name, address, city, states, zipcode, email_address, patient_number, birthdate)
	VALUES (11, 'Qing', 'Orlando', 'Panda Land', 'Tokyo', 'Japan', '3334477', 'pan@gmail', '3333333000', '2023/09/10');

INSERT INTO patient(
	user_id, first_name, last_name, address, city, states, zipcode, email_address, patient_number, birthdate)
	VALUES (12, 'David', 'Orlando', 'David Land', 'Tokyo', 'Japan', '33333', 'pan@gmail', '3333333000', '2023/09/11');
----------------------------------------------------------------------------------------------------------------------------
-- review
INSERT INTO review(
	review_title, review_body, review_rating, review_date, doctor_id, patient_id)
	VALUES ('Qing is the BEST doctor ever!', 'I LOVE HER!!!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Qing'), (SELECT patient_id FROM patient WHERE patient.first_name='Anthony'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('GREAT SERVICE!', 'YAY!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Qing'), (SELECT patient_id FROM patient WHERE patient.first_name='David'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('OMG! SO GOOD', 'YOU WOULD NOT BELIEVE HOW GOOD THIS SERVICE IS', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Qing'), (SELECT patient_id FROM patient WHERE patient.first_name='Max'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('YAY', 'CANCER CURED!!!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), (SELECT patient_id FROM patient WHERE patient.first_name='Jordan'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('WOOHOO', 'BEST SERVICE EVER', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), (SELECT patient_id FROM patient WHERE patient.first_name='Anthony'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('Thank you!', 'He is the best!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), (SELECT patient_id FROM patient WHERE patient.first_name='Qing'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('Salaj MADE ME SMARTER!', 'Incredible service! He cured my brain cancer!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), (SELECT patient_id FROM patient WHERE patient.first_name='Max'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('HE DA BEST!', 'SEE TITLE', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), (SELECT patient_id FROM patient WHERE patient.first_name='Jordan'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('It was pretty good!', 'I had to wait a little!', 4, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), (SELECT patient_id FROM patient WHERE patient.first_name='David'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('YAY', 'CANCER CURED!!!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Jordan'), (SELECT patient_id FROM patient WHERE patient.first_name='Jordan'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('WOOHOO', 'BEST SERVICE EVER', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Jordan'), (SELECT patient_id FROM patient WHERE patient.first_name='Anthony'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('Thank you!', 'He is the best!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Jordan'), (SELECT patient_id FROM patient WHERE patient.first_name='Qing'));


INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('YAY', 'CANCER CURED!!!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), (SELECT patient_id FROM patient WHERE patient.first_name='Jordan'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('WOOHOO', 'BEST SERVICE EVER', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), (SELECT patient_id FROM patient WHERE patient.first_name='Anthony'));

INSERT INTO review(
review_title, review_body, review_rating, review_date, doctor_id, patient_id)
VALUES ('Thank you!', 'He is the best!', 5, '2023/09/08', (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), (SELECT patient_id FROM patient WHERE patient.first_name='Qing'));
-- appointment
INSERT INTO appointment(
	patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
	VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Anthony'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), 30, 'brain fog1', '2023/04/21', '11:00:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Max'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), 30, 'brain fog2', '2023/04/21', '11:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='David'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), 30, 'brain fog3', '2023/04/21', '10:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Jordan'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Dom'), 30, 'brain fog4', '2023/04/21', '13:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Anthony'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), 30, 'WOOOO1', '2023/04/21', '9:00:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='David'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), 30, 'WOOOO2', '2023/04/21', '9:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Max'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Salaj'), 30, 'WOOOO3', '2023/04/21', '11:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Jordan'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), 30, 'hiya1', '2023/04/21', '11:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Max'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), 30, 'hiya2', '2023/04/21', '13:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='David'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Youngjin'), 30, 'hiya3', '2023/04/21', '15:30:00');


INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Anthony'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Jordan'), 30, '420', '2023/04/21', '11:30:00');


INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='David'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Jordan'), 30, '420', '2023/04/21', '14:30:00');


INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Anthony'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Qing'), 30, 'lol', '2023/04/21', '15:30:00');

INSERT INTO appointment(
patient_id, doctor_id, appointment_duration, description, appointment_date, appointment_time)
VALUES ((SELECT patient_id FROM patient WHERE patient.first_name='Max'), (SELECT doctor_id FROM doctor WHERE doctor.first_name='Qing'), 30, 'lol', '2023/04/21', '11:30:00');
-- doctor_time
INSERT INTO public.doctor_time(
	doctor_id, office_date, start_time, end_time)
	VALUES (1, '2023/04/21', '8:00:00', '17:00:00');

INSERT INTO public.doctor_time(
	doctor_id, office_date, start_time, end_time)
	VALUES (2, '2023/04/21', '8:00:00', '17:00:00');

INSERT INTO public.doctor_time(
	doctor_id, office_date, start_time, end_time)
	VALUES (3, '2023/04/21', '8:00:00', '17:00:00');

INSERT INTO public.doctor_time(
	doctor_id, office_date, start_time, end_time)
	VALUES (4, '2023/04/21', '8:00:00', '17:00:00');

INSERT INTO public.doctor_time(
	doctor_id, office_date, start_time, end_time)
	VALUES (5, '2023/04/21', '8:00:00', '17:00:00');


COMMIT TRANSACTION;
