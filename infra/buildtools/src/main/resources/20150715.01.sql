CREATE TABLE person (personId SERIAL PRIMARY KEY,birthday DATE NOT NULL, employmentStatus VARCHAR(5) NOT NULL, gwa REAL NOT NULL, gender VARCHAR(15) NOT NULL);

CREATE TABLE name (personId INT PRIMARY KEY, firstName VARCHAR(100) NOT NULL, middleName VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, suffix VARCHAR(100) NOT NULL, title VARCHAR(100) NOT NULL, CONSTRAINT fk_name_person FOREIGN KEY (personId) REFERENCES person (personId));

CREATE TABLE address (personId INT PRIMARY KEY, stNo INT NULL, brgy VARCHAR(355) NULL, subdivision VARCHAR(355) NULL, city VARCHAR(355) NULL, zipcode INT NULL, CONSTRAINT fk_address_person FOREIGN KEY (personId) REFERENCES person (personId));

CREATE TABLE contact (contactId SERIAL PRIMARY KEY, description VARCHAR(20) NOT NULL, number BIGINT NOT NULL);

