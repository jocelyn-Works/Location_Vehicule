TRUNCATE TABLE ReservationLocationVehicule.reservation;
TRUNCATE TABLE ReservationLocationVehicule.reservation_seq;

INSERT INTO ReservationLocationVehicule.reservation VALUES(1, '2025-01-05', 500 , '2024-12-24', 700,  1, 1, 550);
INSERT INTO ReservationLocationVehicule.reservation VALUES(2, '2025-02-01', 1000 , '2025-01-06', 900, 2, 2, 1000);
INSERT INTO ReservationLocationVehicule.reservation VALUES(3, '2025-07-02', 700, '2025-06-20', 750, 3, 3, 700);
ALTER TABLE ReservationLocationVehicule.reservation AUTO_INCREMENT=4;
INSERT INTO ReservationLocationVehicule.reservation_seq VALUES (4);