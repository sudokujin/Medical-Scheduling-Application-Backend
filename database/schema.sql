-- step 1
BEGIN TRANSACTION;
DROP TABLE IF EXISTS review, appointment, patient, doctor, users, doctor_time;
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE doctor (
	doctor_id SERIAL NOT NULL,
	user_id int NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	specialty varchar(50) NOT NULL,
	suite_number int NOT NULL,
	costPerHour int NOT NULL,
	phone_number varchar(50) NOT NULL,
	CONSTRAINT pk_doctor_doctor_id PRIMARY KEY (doctor_id),
	CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE patient (
	patient_id SERIAL NOT NULL,
	user_id int NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	address varchar(100) NOT NULL,
	city varchar(50) NOT NULL,
	states varchar(50) NOT NULL,
	zipcode varchar(50) NOT NULL,
	email_address varchar(50) NOT NULL,
	patient_number varchar(50) NOT NULL,
	birthdate date NOT NULL,
	CONSTRAINT pk_patient_patient_id PRIMARY KEY (patient_id),
	CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE appointment (
	appointment_id SERIAL NOT NULL,
	patient_id int NOT NULL,
	doctor_id int NOT NULL,
	appointment_duration int NOT NULL,
	description varchar(100) NOT NULL,
	appointment_date date NOT NULL,
	appointment_time time NOT NULL,

	CONSTRAINT pk_appointment_appointment_id PRIMARY KEY (appointment_id),
	CONSTRAINT FK_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
	CONSTRAINT FK_patient FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);
CREATE TABLE review (
	review_id SERIAL NOT NULL,
	review_title varchar(500) NOT NULL,
	review_body varchar(500) NOT NULL,
	review_rating int NOT NULL,
	review_date date NOT NULL,
	doctor_id int NOT NULL,
	patient_id int NOT NULL,

	CONSTRAINT pk_review_review_id PRIMARY KEY (review_id),
	CONSTRAINT FK_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
	CONSTRAINT FK_patient FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);

CREATE TABLE doctor_time (
	doctor_time_id SERIAL NOT NULL,
	doctor_id int NOT NULL,
	office_date date NOT NULL,
	start_time time NOT NULL,
	end_time time NOT NULL,

	CONSTRAINT pk_doctor_time_id PRIMARY KEY (doctor_time_id),
	CONSTRAINT FK_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);

COMMIT TRANSACTION;