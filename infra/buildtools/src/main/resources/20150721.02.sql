DROP TABLE personcontacts;

ALTER TABLE contact ADD personId INT NOT NULL;

ALTER TABLE contact ADD CONSTRAINT fk_contact_person FOREIGN KEY (personId) REFERENCES person (personId);

