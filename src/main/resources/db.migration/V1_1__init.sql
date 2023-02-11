DROP TABLE IF EXISTS scores;
DROP TABLE IF EXISTS quotes;
DROP TABLE IF EXISTS authentications;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    create_date TIMESTAMP NOT NULL
);

CREATE TABLE authentications (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(100) NOT NULL,
    id_user VARCHAR(50) NOT NULL REFERENCES users(id)
);

CREATE TABLE quotes (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    id_user VARCHAR(50) NOT NULL REFERENCES users(id),
    quote VARCHAR NOT NULL,
    date TIMESTAMP NOT NULL
);

CREATE TABLE scores (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    id_quotes VARCHAR(50) NOT NULL REFERENCES quotes(id),
    scores_positive int,
    scores_negative int,
    update_date TIMESTAMP NOT NULL
);

INSERT INTO users VALUES ('1', 'Alex', 'Litvinov','2020-01-01 23.59.59');
INSERT INTO users VALUES ('2', 'Sergey', 'Popov', '2020-01-10 20.00.59');
INSERT INTO users VALUES ('3', 'Anton', 'Adencov', '2020-01-15 21.09.59');
INSERT INTO authentications VALUES ('1', 'alex@mail.ru', '0000', '1');
INSERT INTO authentications VALUES ('2', 'sergey@gmail.com', '1111', '2');
INSERT INTO authentications VALUES ('3', 'anton@yandex.com', '1234', '3');
INSERT INTO quotes VALUES ('1', '1', 'Hello', '2020-12-31 23.59.59');
INSERT INTO quotes VALUES ('2', '1', 'I am fine ', '2021-01-01 10.00.00');
INSERT INTO quotes VALUES ('3', '2', 'It is life', '2021-01-02 08.59.59');
INSERT INTO scores VALUES ('1', '1', 5, 0, '2021-01-02 08.59.59');
INSERT INTO scores VALUES ('2', '2', 6, 9, '2021-01-02 08.59.59');