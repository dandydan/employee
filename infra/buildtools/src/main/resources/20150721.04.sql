ALTER TABLE person DROP COLUMN employmentStatus;

ALTER TABLE person ADD employed BOOLEAN NOT NULL;
