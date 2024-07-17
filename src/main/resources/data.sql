INSERT INTO peoples (apartment_number, first_name, phone_number, second_name)
VALUES (3, 'Martin', 08932131234, 'Petrov'),
       (3, 'Yana', 0894431234, 'Petrova'),
       (4, 'Georgi', 0895831234, 'Georgiev'),
       (6, 'Petar', 08934331234, 'Ivanov'),
       (5, 'Ivan', 0893284234, 'Rusev'),
       (9, 'Ilian', 0893234234, 'Ganchev'),
       (8, 'Atanas', 0893901234, 'Andonov');

INSERT INTO apartments (floor, number) VALUES
                                           (2,3),
                                           (2,4),
                                           (3,5),
                                           (3,6),
                                           (4,9),
                                           (4,8);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO apartments_peoples (apartment_id, peoples_id)
VALUES (3,1),(3,2),(4,3),(6,4),(5,5),(9,6),(8,7)
