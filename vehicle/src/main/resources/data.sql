TRUNCATE TABLE VehicleLocationVehicule.maintenance;
TRUNCATE TABLE VehicleLocationVehicule.maintenance_seq;

DELETE FROM VehicleLocationVehicule.vehicle WHERE id < 9999999;
TRUNCATE TABLE VehicleLocationVehicule.vehicle_seq;



INSERT INTO VehicleLocationVehicule.vehicle VALUES(1, 'Ford', 'Red' , 0, 0.20,  'Focus', 60.0, 6.5, 20000, 'utilitaire', 'EF-744-LH', 4);
INSERT INTO VehicleLocationVehicule.vehicle VALUES(2, 'Nissan', 'Black' , 0, 0.15, 'NissanVoiture', 50.0, 7.5, 30000, 'voiture', 'EG-754-KH', 0);
INSERT INTO VehicleLocationVehicule.vehicle VALUES(3, 'Renault', 'White' , 1600, 0.10, 'RenaultMoto', 40.0, 5.2, 10000, 'moto', 'YC-139-MM', 0);
ALTER TABLE VehicleLocationVehicule.vehicle AUTO_INCREMENT=4;
INSERT INTO VehicleLocationVehicule.vehicle_seq VALUES (4);

INSERT INTO VehicleLocationVehicule.maintenance VALUES(1, '2024-07-20', '2024-07-23', 20000, 'les suspensions doivent etre changer + 2 ans', true, '2024-07-20', '2024-07-23', 1);
INSERT INTO VehicleLocationVehicule.maintenance VALUES(2, '2024-06-18', '2024-06-19', 30000, 'les pneus doit etre changer + 1 ans', true, '2024-06-18', '2024-06-19', 2);
INSERT INTO VehicleLocationVehicule.maintenance VALUES(3, '2024-05-22', '2024-05-28', 10000, 'chaîne motos +1000 km', true, '2024-05-22', '2024-05-28', 3);
ALTER TABLE VehicleLocationVehicule.maintenance AUTO_INCREMENT=4;
INSERT INTO VehicleLocationVehicule.maintenance_seq VALUES (4);