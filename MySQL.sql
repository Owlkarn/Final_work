CREATE DATABASE `Друзья_человека`;

USE `Друзья_человека`;

CREATE TABLE Животные (
    id INT AUTO_INCREMENT PRIMARY KEY,
    дата_рождения DATE,
    имя VARCHAR(255)
);

CREATE TABLE Домашние_животные (
    id INT AUTO_INCREMENT PRIMARY KEY,
    animal_id INT,
    FOREIGN KEY (animal_id) REFERENCES Животные(id)
);

CREATE TABLE Вьючные_животные (
    id INT AUTO_INCREMENT PRIMARY KEY,
    animal_id INT,
    FOREIGN KEY (animal_id) REFERENCES Животные(id)
);

CREATE TABLE Собаки (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pet_id INT,
    дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES Домашние_животные(id)
);

CREATE TABLE Кошки (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pet_id INT,
    дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES Домашние_животные(id)
);

CREATE TABLE Хомяки (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pet_id INT,
    дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES Домашние_животные(id)
);

CREATE TABLE Лошади (
    id INT AUTO_INCREMENT PRIMARY KEY,
    beast_id INT,
	дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (beast_id) REFERENCES Вьючные_животные(id)
);

CREATE TABLE Верблюды (
    id INT AUTO_INCREMENT PRIMARY KEY,
    beast_id INT,
	дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (beast_id) REFERENCES Вьючные_животные(id)
);

CREATE TABLE Ослы (
    id INT AUTO_INCREMENT PRIMARY KEY,
    beast_id INT,
	дата_рождения DATE,
    имя VARCHAR(255),
    команда_1 VARCHAR(255),
    команда_2 VARCHAR(255),
    FOREIGN KEY (beast_id) REFERENCES Вьючные_животные(id)
);

INSERT INTO Животные (дата_рождения, имя)
VALUES ('2019-05-15', 'Тузик'),
	   ('2021-03-07', 'Мурка'),
       ('2022-02-02', 'Хома'),
       ('2017-09-22', 'Буцефал'),
       ('2018-11-11', 'Али'),
       ('2019-07-30', 'Осел');
       
INSERT INTO Домашние_животные (animal_id)
VALUES ('1'),
	   ('2'),
       ('3');
       
INSERT INTO Вьючные_животные (animal_id)
VALUES ('4'),
	   ('5'),
       ('6');

INSERT INTO Собаки (pet_id, дата_рождения, имя, команда_1, команда_2)
VALUES (1, '2019-05-15', 'Тузик', 'Сидеть', 'Фас');

INSERT INTO Кошки (pet_id, дата_рождения, имя, команда_1, команда_2)
VALUES (2, '2021-03-07', 'Мурка', 'Мурлыкать', 'Царапать');

INSERT INTO Хомяки (pet_id, дата_рождения, имя, команда_1, команда_2)
VALUES (3, '2022-02-02', 'Хома', 'Кружиться в колесе', 'Кушать семечки');

INSERT INTO Лошади (beast_id, дата_рождения, имя, команда_1, команда_2)
VALUES (1, '2017-09-22', 'Буцефал', 'Галоп', 'Вперед');

INSERT INTO Верблюды (beast_id, дата_рождения, имя, команда_1, команда_2)
VALUES (2, '2018-11-11', 'Али', 'Переносить грузы', 'Встать в караван');

INSERT INTO Ослы (beast_id, дата_рождения, имя, команда_1, команда_2)
VALUES (3, '2019-07-30', 'Осел', 'Таскать грузы', 'Есть морковку');

DELETE FROM Верблюды;

CREATE TABLE Лошади_и_ослы AS
SELECT * FROM Лошади
UNION ALL
SELECT * FROM Ослы;

DROP TABLE Лошади;
DROP TABLE Ослы;

CREATE TABLE молодые_животные AS
SELECT
    id,
    дата_рождения,
    имя,
    TIMESTAMPDIFF(YEAR, дата_рождения, CURDATE()) - (DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(дата_рождения, '%m%d')) AS возраст_годы,
    TIMESTAMPDIFF(MONTH, дата_рождения, CURDATE()) - (DATE_FORMAT(CURDATE(), '%d') < DATE_FORMAT(дата_рождения, '%d')) AS возраст_месяцы
FROM (
    SELECT id, дата_рождения, имя FROM Собаки
    UNION ALL
    SELECT id, дата_рождения, имя FROM Кошки
    UNION ALL
    SELECT id, дата_рождения, имя FROM Хомяки
    UNION ALL
    SELECT id, дата_рождения, имя FROM Лошади_и_ослы
) AS все_животные
WHERE дата_рождения <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
AND дата_рождения >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

ALTER TABLE Собаки ADD class VARCHAR(20) NOT NULL DEFAULT 'Собаки';
ALTER TABLE Кошки ADD class VARCHAR(20) NOT NULL DEFAULT 'Кошки';
ALTER TABLE Хомяки ADD class VARCHAR(20) NOT NULL DEFAULT 'Хомяки';
ALTER TABLE Лошади_и_ослы ADD class VARCHAR(20) NOT NULL DEFAULT 'Лошади_и_ослы';

CREATE TABLE Все_животные AS
SELECT id, дата_рождения, имя, команда_1, команда_2, class FROM Собаки
UNION ALL
SELECT id, дата_рождения, имя, команда_1, команда_2, class FROM Кошки
UNION ALL
SELECT id, дата_рождения, имя, команда_1, команда_2, class FROM Хомяки
UNION ALL
SELECT id, дата_рождения, имя, команда_1, команда_2, class FROM Лошади_и_ослы;

SELECT * FROM Все_животные;
