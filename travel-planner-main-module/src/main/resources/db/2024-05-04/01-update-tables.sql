-- liquibase formatted sql

-- changeset jasokolowska:1
ALTER TABLE route
    ALTER COLUMN destination_id TYPE VARCHAR(1000);

-- changeset jasokolowska:2
ALTER TABLE route
    ALTER COLUMN origin_id TYPE VARCHAR(1000);

-- changeset jasokolowska:3
ALTER TABLE flight
    ALTER COLUMN link TYPE VARCHAR(4000);





