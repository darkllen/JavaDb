create table publisher
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    unique unique_publisher_name (name)
);

create table book
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    description     varchar (255) not null,
    pages_num       int not null,
    publisher_id    int not null,
    palitur_type    int not null,
    price           int not null,
    constraint fk_book_pub foreign key (publisher_id) references publisher(id) on delete cascade,
    unique unique_book_name (name)
);

create table genre
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    unique unique_genre_name (name)
);

create table book_to_genre
(
    book_id              int not null,
    genre_id             int not null,
    PRIMARY KEY (book_id, genre_id),
    constraint fk_book_id foreign key (book_id) references book(id),
    constraint fk_genre_id foreign key (genre_id) references genre(id)
);

create table author
(
    id              int primary key auto_increment,
    name            varchar(30) not null,
    unique unique_author_name (name)
);

create table author_book
(
    book_id              int not null,
    author_id            int not null,
    PRIMARY KEY (book_id, author_id),
    constraint fk_bookk_id foreign key (book_id) references book(id),
    constraint fk_author_id foreign key (author_id) references author(id)
);


INSERT INTO `genre` (`name`) VALUES ('genre1');
INSERT INTO `genre` (`name`) VALUES ('genre2');

INSERT INTO `publisher` (`name`) VALUES ('publisher1');
INSERT INTO `publisher` (`name`) VALUES ('publisher2');

INSERT INTO `author` (`name`) VALUES ('author1');
INSERT INTO `author` (`name`) VALUES ('author2');

INSERT INTO `book` (name, description, pages_num, publisher_id, palitur_type,price) VALUES ('book1', 'descr1', 10, 1, 1, 100);
INSERT INTO `book` (name, description, pages_num, publisher_id, palitur_type,price) VALUES ('book2', 'descr2', 20, 1, 2, 200);
INSERT INTO `book` (name, description, pages_num, publisher_id, palitur_type,price) VALUES ('book3', 'descr3', 30, 2, 2, 60);

INSERT INTO `book_to_genre` (`book_id`, `genre_id`) VALUES (1, 2);
INSERT INTO `book_to_genre` (`book_id`, `genre_id`) VALUES (1, 1);
INSERT INTO `book_to_genre` (`book_id`, `genre_id`) VALUES (2, 2);
INSERT INTO `book_to_genre` (`book_id`, `genre_id`) VALUES (3, 1);

INSERT INTO `author_book` (`book_id`, `author_id`) VALUES (1, 2);
INSERT INTO `author_book` (`book_id`, `author_id`) VALUES (1, 1);
INSERT INTO `author_book` (`book_id`, `author_id`) VALUES (2, 2);
INSERT INTO `author_book` (`book_id`, `author_id`) VALUES (3, 1);