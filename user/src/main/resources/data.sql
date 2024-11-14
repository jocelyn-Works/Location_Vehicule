TRUNCATE TABLE UserLocationVehicule.user;
TRUNCATE TABLE UserLocationVehicule.user_seq;


INSERT INTO UserLocationVehicule.user VALUES(1, '1999-11-28', '2021-11-28' , 'Shima', 'Hiro', 'des1235fj');
INSERT INTO UserLocationVehicule.user VALUES(2, '1945-03-09', '1947-03-09', 'Zaki', 'Naga', 'hytf21sct5');
INSERT INTO UserLocationVehicule.user VALUES(3,'1939-04-05', '1943-06-19', '12cdrt8z5', 'Adolfi', '12cdrt8z5');
ALTER TABLE UserLocationVehicule.user AUTO_INCREMENT=4;

ALTER TABLE UserLocationVehicule.user_seq AUTO_INCREMENT=4;
INSERT INTO UserLocationVehicule.user_seq VALUES (4);