-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:populate-role-table
INSERT INTO `bookstorerest`.`role` (`id`, `created`, `name`) VALUES ('1', '2023-12-20 19:33:15', 'ROLE_USER');
INSERT INTO `bookstorerest`.`role` (`id`, `created`, `name`) VALUES ('2', '2023-12-20 19:33:15', 'ROLE_ADMIN');
