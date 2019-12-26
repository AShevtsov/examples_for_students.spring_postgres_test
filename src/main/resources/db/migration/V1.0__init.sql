CREATE SEQUENCE user_id_seq;

CREATE TABLE users(
    user_id INTEGER PRIMARY KEY DEFAULT nextval('user_id_seq'),
    name varchar(30),
    surname varchar(30),
    age int
);