
# set foreign_key_checks = 0;
# # DELETE FROM UserLocationVehicule.user_roles WHERE user_id < 9999;
# # TRUNCATE TABLE UserLocationVehicule.roles;
# # TRUNCATE TABLE UserLocationVehicule.user;
# # TRUNCATE TABLE UserLocationVehicule.user_seq;
# set foreign_key_checks = 1;

    INSERT IGNORE INTO UserLocationVehicule.roles(name) VALUES('ROLE_USER');
    INSERT IGNORE INTO UserLocationVehicule.roles(name) VALUES('ROLE_MODERATOR');
    INSERT IGNORE INTO UserLocationVehicule.roles(name) VALUES('ROLE_ADMIN');


INSERT IGNORE INTO UserLocationVehicule.user_seq VALUES (1);
INSERT IGNORE INTO UserLocationVehicule.user VALUES(1, '2021-11-28', '2021-11-28' ,'test@gmail.com', 'Hiro', 'Shima','des1235fj', 'ze6789');


