-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:create-user-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='bookstorerest.user';
CREATE TABLE `bookstorerest`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `username` VARCHAR(25) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-role-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='role';
CREATE TABLE `bookstorerest`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL DEFAULT NOW(),
  `name` VARCHAR(25) NOT NULL UNIQUE,
  PRIMARY KEY (`id`));



-- changeset Khasanboy-Akbarov:create-user-role-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='user_role';
    CREATE TABLE `bookstorerest`.`user_role` (
      `user_id` INT NOT NULL,
      `role_id` INT NOT NULL,
      PRIMARY KEY (`user_id`, `role_id`),
      INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
      CONSTRAINT `user_id`
        FOREIGN KEY (`user_id`)
        REFERENCES `bookstorerest`.`user` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `role_id`
        FOREIGN KEY (`role_id`)
        REFERENCES `bookstorerest`.`role` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);