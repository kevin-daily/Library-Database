CREATE DATABASE library_books;

use library_books;

CREATE TABLE books_tbl 
  ( 
     book_number   INT NOT NULL AUTO_INCREMENT, 
     book_author   VARCHAR(100) NOT NULL, 
     book_title    VARCHAR(100) NOT NULL, 
     book_year     SMALLINT NOT NULL, 
     book_genre    VARCHAR(50) NOT NULL, 
     book_ISBN     BIGINT NOT NULL,
     book_quantity SMALLINT unsigned NOT NULL,
     is_checked    BOOLEAN NOT NULL,
     book_location VARCHAR(50) NOT NULL,
     PRIMARY KEY ( 
	book_number,
	book_title,
	book_isbn 
     )
  ); 
  
CREATE TABLE users_tbl
  ( 
     user_number INT NOT NULL AUTO_INCREMENT,
     username    VARCHAR(100) NOT NULL, 
     password    VARCHAR(100) NOT NULL, 
     first_name  VARCHAR(100) NOT NULL, 
     last_name   VARCHAR(100) NOT NULL, 
     address     VARCHAR(100) NOT NULL, 
     phone       INT unsigned NOT NULL, 
     email       VARCHAR(100) NOT NULL,
     PRIMARY KEY (
	user_number, 
	email 
     )
  ); 

CREATE TABLE user_checkout_tbl
  (
     user_number  INT NOT NULL,
     book_number  INT NOT NULL
  );
