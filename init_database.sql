CREATE DATABASE amazing_quiz;
\c amazing_quiz
CREATE TABLE questions (id int, author varchar(255), content varchar(255), answers varchar(255)[], good_answers int[], type_quiz int);
CREATE TABLE users (id int, name varchar(255), score int);