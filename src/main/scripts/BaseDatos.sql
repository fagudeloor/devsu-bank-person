DROP DATABASE IF EXISTS challenge_devsu_person;
DROP USER IF EXISTS `user_devsu_person`@`%`;
CREATE DATABASE IF NOT EXISTS challenge_devsu_person CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `user_devsu_person`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `challenge_devsu_person`.* TO `user_devsu_person`@`%`;
FLUSH PRIVILEGES;

CREATE TABLE challenge_devsu_person.person (
id varchar(36) NOT NULL,
document_type varchar(32) NOT NULL,
document_number bigint,
first_last_name varchar(36) NOT NULL,
second_last_name varchar(36),
given_name varchar(36) NOT NULL,
main_address varchar(120) NOT NULL,
second_address varchar(120),
phone_number varchar(36) NOT NULL,
second_phone_number varchar(36),
created_date timestamp NOT NULL,
PRIMARY KEY (id),
CONSTRAINT dc_person UNIQUE (document_type, document_number)
);

CREATE TABLE challenge_devsu_person.customer (
id varchar(36) NOT NULL,
person_id varchar(36) NOT NULL UNIQUE,
user_name varchar(16) NOT NULL,
password double NOT NULL,
created_date timestamp NOT NULL,
state varchar(16) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (person_id) REFERENCES challenge_devsu_person.person(id)
);

