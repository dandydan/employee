CREATE TABLE role (roleId SERIAL PRIMARY KEY, pos VARCHAR(30) NOT NULL);
CREATE TABLE personroles (personId INT NOT NULL, roleId INT NOT NULL);

INSERT INTO role (pos) VALUES ('President'),('Manager'),('Team Leader'),('Senior Developer'),('Junior Developer');
