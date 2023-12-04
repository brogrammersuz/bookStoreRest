-- liquibase formatted sql

-- changeset Muzaffar-Ashurov:create-publisher-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='publisher';
CREATE TABLE `bookstorerest`.`publisher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `name` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NULL,
  `zip` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

  -- changeset Muzaffar-Ashurov:create-book-table
  -- preconditions onFail:MARK_RAN
  -- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='book';
  CREATE TABLE `bookstorerest`.`book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `isbn` VARCHAR(45) NOT NULL,
    `publisher_id` INT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC) VISIBLE,
    CONSTRAINT `publisher_id`
      FOREIGN KEY (`publisher_id`)
      REFERENCES `bookstorerest`.`publisher` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

  -- changeset Muzaffar-Ashurov:create-author-table
  -- preconditions onFail:MARK_RAN
  -- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='author';
  CREATE TABLE `bookstorerest`.`author` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));

  -- changeset Muzaffar-Ashurov:create-author_book-table
    -- preconditions onFail:MARK_RAN
    -- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='author_book';
    CREATE TABLE `bookstorerest`.`author_book` (
      `author_id` INT NOT NULL,
      `book_id` INT NOT NULL,
      PRIMARY KEY (`author_id`, `book_id`),
      INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
      CONSTRAINT `author_id`
        FOREIGN KEY (`author_id`)
        REFERENCES `bookstorerest`.`author` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `book_id`
        FOREIGN KEY (`book_id`)
        REFERENCES `bookstorerest`.`book` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);