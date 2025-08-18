INSERT INTO CAR (id, model, immat, available) VALUES ('v1', 'Peugeot 208', 'XX-001-XX', true);
INSERT INTO CAR (id, model, immat, available) VALUES ('v2', 'Renault Clio', 'ZZ-024-BB',true);
INSERT INTO CAR (id, model, immat, available) VALUES ('v3', 'Toyota Yaris', 'XY-123-ZA',false);

INSERT INTO CUSTOMER (id, lastName, firstName, address, email, phone, dateBirth, licenseCar) 
VALUES (1, 'MARTIN', 'Eric', '20 RUE DE LA CHAUSSEE 57000 METZ', 'eric.martin@gmail.com', '+33600000000', '1986-05-21', '000568974');

INSERT INTO CUSTOMER (id, lastName, firstName, address, email, phone, dateBirth, licenseCar) 
VALUES (2, 'DUPONT', 'Marie', '12 RUE JOSEPH CUGNOT 57070 METZ', 'mdupont@gmail.com', '+33600000111', '1995-06-12', '1234567890');

INSERT INTO LEASE_CONTRACT (id, refLeaseContract, startContract, endContract, idCar, idCustomer, statusLeaseContract)
VALUES (1, '2025-07001', '2025-07-19 08:30:00.000', '2025-07-19 18:30:00.000', 'v1', 2, 'CLOSED');

INSERT INTO LEASE_CONTRACT(id, refLeaseContract, startContract, endContract, idCar, idCustomer, statusLeaseContract)
VALUES (2, '2025-08002', '2025-08-18 14:00:00.000', '2025-08-21 14:00:00.000', 'v3', 1, 'IN PROGRESS');