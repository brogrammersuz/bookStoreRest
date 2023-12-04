-- liquibase formatted sql

-- changeset Muzaffar-Ashurov:populate-publisher-table
INSERT INTO `bookstorerest`.`publisher` (`id`, `name`, `address`, `city`, `state`, `zip`) VALUES
(1, 'SFG Publishing','Russia', 'St Petersburg', 'FL', 'RUS'),
(2, 'Триумф','Россия', 'Москва','FT', 'RUS'),
(3, 'Toshkent Nashr','Uzbekistan', 'Tashkent','IR','UZB');

-- changeset Muzaffar-Ashurov:populate-author-table
INSERT INTO `bookstorerest`.`author` (`id`, `first_name`, `last_name`) VALUES
(1, 'Eric', 'Evans'),
(2, 'Rod', 'Johnson'),
(3, 'Robert', 'Pattinson'),
(4, 'Bobur', 'Mirzo'),
(5, 'Alisher', 'Navoiy');

-- changeset Muzaffar-Ashurov:populate-book-table
INSERT INTO `bookstorerest`.`book` (`id`, `publisher_id`, `title`, `isbn`) VALUES
(1, 1, 'J2EE Development without EJB', '3939459459'),
(2, 1, 'Domain Driven Design', '242121212'),
(3, 2, 'Война миров', '368-749654'),
(4, 3, 'Ming bir kecha', '9878-362'),
(5, 3, 'Xamsa', '8974-362');

-- changeset Muzaffar-Ashurov:populate-author_book-table
INSERT INTO `bookstorerest`.`author_book` (`author_id`, `book_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);