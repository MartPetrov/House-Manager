# INSERT INTO peoples (apartment_number, first_name, phone_number, second_name)
# VALUES (3, 'Martin', 08932131234, 'Petrov'),
#        (3, 'Yana', 0894431234, 'Petrova'),
#        (4, 'Georgi', 0895831234, 'Georgiev'),
#        (6, 'Petar', 08934331234, 'Ivanov'),
#        (5, 'Ivan', 0893284234, 'Rusev'),
#        (9, 'Ilian', 0893234234, 'Ganchev'),
#        (8, 'Atanas', 0893901234, 'Andonov');
#
# INSERT INTO apartments (floor, number)
# VALUES (2, 3),
#        (2, 4),
#        (3, 5),
#        (3, 6),
#        (4, 9),
#        (4, 8);
# SET FOREIGN_KEY_CHECKS = 0;
# INSERT INTO apartments_peoples (apartment_id, peoples_id)
# VALUES (3, 1),
#        (3, 2),
#        (4, 3),
#        (6, 4),
#        (5, 5),
#        (9, 6),
#        (8, 7);
#
# insert into bills(first_number, second_number,date,sum, month,type_of_bill)
# values
#     (1312351234,123123123,'30.06.2024',26.74, 7, 'ELECTRICITY'),
#     (1312551234,123126723,'30.05.2024',26.74, 6, 'ELECTRICITY'),
#     (1312751234,123129723,'30.04.2024',23.95, 5, 'ELECTRICITY'),
#     (1314395534,123122523,'30.03.2024',27.54, 4, 'ELECTRICITY'),
#     (1312366234,123156123,'30.02.2024',29.14, 3, 'ELECTRICITY'),
#     (1313567734,123187123,'30.01.2024',20.84, 2, 'ELECTRICITY'),
#     (1323235234,123100123,'30.08.2023',26.74, 9, 'ELECTRICITY');
#
# INSERT INTO apartments_bills (apartment_id, bills_id)
# VALUES (1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),
#        (2, 1),(2,2),(2,3),(2,4),(2, 5),(2, 6),(2, 7),
#        (3, 1),(3,2),(3,3),(3,4),(3, 5),(3, 6),(3, 7),
#        (4, 1),(4,2),(4,3),(4,4),(4, 5),(4, 6),(4, 7),
#        (5, 1),(5,2),(5,3),(5,4),(5, 5),(5, 6),(5, 7),
#        (6, 1),(6,2),(6,3),(6,4),(6, 5),(6, 6),(6, 7);
#
#

INSERT INTO roles VALUES (1, 'USER'), (2, 'ADMIN'), (3, 'MODERATOR');


INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);

SET FOREIGN_KEY_CHECKS = 0;
COMMIT