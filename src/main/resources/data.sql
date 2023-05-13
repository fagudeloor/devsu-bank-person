DELETE FROM  challenge_devsu_person.customer;
DELETE FROM  challenge_devsu_person.person;

INSERT INTO PERSON (id, created_date, document_type, document_number, first_last_name, second_last_name, given_name, main_address, second_address, phone_number, second_phone_number) VALUES ('0a818933-087d-47f2-ad83-2f986ed087eb', CURRENT_TIMESTAMP, 'CC', 10365244785,'Lema', '', 'Jose', 'Otavalo sn y principal', '', 098254785, null);
INSERT INTO PERSON (id, created_date, document_type, document_number, first_last_name, second_last_name, given_name, main_address, second_address, phone_number, second_phone_number) VALUES ('a712d914-61ea-4623-8bd0-32c0f6545bfd', CURRENT_TIMESTAMP, 'CC', 78562362,'Montalvo', '', 'Marianela', 'Amazonas y NNUU', '', 097548965, null);
INSERT INTO PERSON (id, created_date, document_type, document_number, first_last_name, second_last_name, given_name, main_address, second_address, phone_number, second_phone_number) VALUES ('026cc3c8-3a0c-4083-a05b-e908048c1b08', CURRENT_TIMESTAMP, 'PP', 1236589652325, 'Osorio', '', 'Juan', '13 de junio y Equinoccial', '', 098874587, null);

INSERT INTO CUSTOMER (id, created_date, person_id, user_name, password, state) VALUES ('64fc03d6-3423-4f63-af08-d843fca6380a', CURRENT_TIMESTAMP, '0a818933-087d-47f2-ad83-2f986ed087eb', 'JoseLema31', '1234', 'true');
INSERT INTO CUSTOMER (id, created_date, person_id, user_name, password, state) VALUES ('a01d1e78-4ac5-4f27-87a4-53e960659b30', CURRENT_TIMESTAMP, 'a712d914-61ea-4623-8bd0-32c0f6545bfd', 'MariM43', '5678', 'true');
INSERT INTO CUSTOMER (id, created_date, person_id, user_name, password, state) VALUES ('a919ecbb-aff9-46a6-84c7-9e5726216c31', CURRENT_TIMESTAMP, '026cc3c8-3a0c-4083-a05b-e908048c1b08', 'JOsorio', '1245', 'true');
