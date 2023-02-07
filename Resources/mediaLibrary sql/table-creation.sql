CREATE TABLE mediaLibrary.books(
	id BIGINT NOT NULL auto_increment unique,
    title VARCHAR(255) not null,
    author VARCHAR(255) not null,
    pagecount bigint not null,
    genre varchar(255) not null,
    primary key (id)
); 