INSERT INTO address(house_number,street,zip,town) VALUES ('1-3','Fürstenallee','33102','Paderborn')
INSERT INTO address(house_number,street,zip,town) VALUES ('9','Arminiusstraße','33175','Bad Lippspringe')
INSERT INTO address(house_number,street,zip,town) VALUES ('257','Detmolder Straße','33175','Bad Lippspringe')
INSERT INTO address(house_number,street,zip,town) VALUES ('42','Die Straße','33102','Paderborn')
INSERT INTO address(house_number,street,zip,town) VALUES ('8','Hardehauserweg','33102','Paderborn')


INSERT INTO courses(name,lecturer,description) values ('System- und Netzwerkprogrammierung','Prof. Dr. Nüßer','Man schieße sich ins Knie!')
INSERT INTO courses(name,lecturer,description) values ('Wirtschaftsrecht','Pelke','Jeder ist mal Verbrecher!')

INSERT INTO courses(name,lecturer,description) values ('Wirtschaftsrecht','Pelke','Jeder ist mal Verbrecher!')
INSERT INTO courses(name,lecturer,description) values ('Anwendungsentwicklung','Heuermann','Java!')
INSERT INTO courses(name,lecturer,description) values ('SRM','Markus von Detten','Sicherheit!')


INSERT INTO students(student_number, first_name, last_name, mail, phone, date_of_birth,ADDRESS_ID,) VALUES ('123456', 'Patrick', 'Star', 'patrick.star@gmail.com', '01008054646', '1996-06-10',1)
INSERT INTO students(student_number, first_name, last_name, mail, phone, date_of_birth,ADDRESS_ID,) VALUES ('789102', 'Petka', 'Panos', 'panos.petka@hotmail.com', '018054646', '1996-04-01',2)
INSERT INTO students(student_number, first_name, last_name, mail, phone, date_of_birth,ADDRESS_ID,) VALUES ('331728', 'Peter', 'Parker', 'peter.parker@web.de', '555080923', '1990-10-10',3)
INSERT INTO students(student_number, first_name, last_name, mail, phone, date_of_birth,ADDRESS_ID,) VALUES ('313453', 'Paul', 'Puma', 'paulpaul@gmx.de', '0176401764', '1980-09-22',4)
INSERT INTO students(student_number, first_name, last_name, mail, phone, date_of_birth,ADDRESS_ID,) VALUES ('121311', 'Pan', 'Pun', 'pan_pun@yahoomail.de', '555731822134', '1999-01-30',5)

INSERT INTO student_course(student_id, course_id) VALUES (1,1)
