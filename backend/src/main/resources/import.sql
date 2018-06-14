insert into category(id, name) values (1, 'food');
insert into category(id, name) values (2, 'medical');
insert into category(id, name) values (3, 'utilities');
insert into category(id, name) values (4, 'clothing');
insert into category(id, name) values (5, 'transportation');
insert into category(id, name) values (6, 'personal');
insert into category(id, name) values (7, 'transfer');
INSERT INTO users (username,password_hash,active,deleted) VALUES ('admin', '$2a$10$d8gv6jyA/He8va6QMSEEmuwpQM1XFKgEgj8TTGxJ13lHBQ6ftT9Bm', TRUE,FALSE); --pass: admin
INSERT INTO users (username,password_hash,active,deleted) VALUES ('user', '$2a$10$w6RGd8eJ1UUkZ9UUCfXAyuSEcFhdGVvcCVaiqgYPSWkVHIdh6HzJy', TRUE,FALSE); --pass: qwerty
insert into transaction(id, description, value, date, category_id) VALUES (1,'pyrki - 1 tona',-467,'2018-01-01',1);
insert into transaction(id, description, value, date, category_id) VALUES (2,'przewóz pyrków',-20,'2018-01-01',5);
insert into transaction(id, description, value, date, category_id) VALUES (3,'cholinex, serevent dysk, flixotide, zyrtec',-200,'2018-01-04',2);
insert into transaction(id, description, value, date, category_id) VALUES (4,'kurs po leki',-15,'2018-01-04',5);
insert into transaction(id, description, value, date, category_id) VALUES (5,'opłaty za prąd, gaz, wode, internet',-666,'2018-01-05',3);
insert into transaction(id, description, value, date, category_id) VALUES (6,'ślubny outfit',-8000,'2018-01-09',4);
insert into transaction(id, description, value, date, category_id) VALUES (7,'podróż poślubna do Zgierza',-30,'2018-01-15',5);
insert into transaction(id, description, value, date, category_id) VALUES (8,'wynajem pokoju w hotelu 2 gwiazdkowym dla 2 osób na 3 dni',-50,'2018-01-15',6);
insert into transaction(id, description, value, date, category_id) VALUES (9,'chleb, smalec, ogórek',-8,'2018-01-16',1);
insert into transaction(id, description, value, date, category_id) VALUES (10,'zapomniana zapłata za telefon',-100,'2018-01-16',3);
insert into transaction(id, description, value, date, category_id) VALUES (11,'wycieczka po Zgierzu',-80,'2018-01-17',5);
insert into transaction(id, description, value, date, category_id) VALUES (12,'chleb, smalec, ogórek',-8.50,'2018-01-18',1);
insert into transaction(id, description, value, date, category_id) VALUES (13,'ucieczka w Bieszczady',-200,'2018-01-18',5);
insert into transaction(id, description, value, date, category_id) VALUES (14,'chleb, dżem, cappy',-9,'2018-01-19',1);
insert into transaction(id, description, value, date, category_id) VALUES (15,'olej rycynowy',-5,'2018-01-20',2);
insert into transaction(id, description, value, date, category_id) VALUES (16,'sukienka, torebka, buty',-350,'2018-01-21',4)
insert into transaction(id, description, value, date, category_id) VALUES (17,'fryzjer',-60,'2018-01-24',6);
insert into transaction(id, description, value, date, category_id) VALUES (18,'karnet na siłownie',-120,'2018-01-25',6);
insert into transaction(id, description, value, date, category_id) VALUES (19,'wypłata',4670,'2018-02-01',7);
insert into transaction(id, description, value, date, category_id) VALUES (20,'przelew od Krzycha',57,'2018-02-02',7);

