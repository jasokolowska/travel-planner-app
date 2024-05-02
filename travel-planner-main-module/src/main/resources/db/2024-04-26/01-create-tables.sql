--liquibase formatted sql

--changeset jasokolowska:1
CREATE TABLE airport
(
    id   BIGSERIAL PRIMARY KEY,
    code VARCHAR(10)  NOT NULL,
    name VARCHAR(255) NOT NULL
);

--changeset jasokolowska:2
CREATE TABLE flight
(
    id        BIGSERIAL PRIMARY KEY,
    city_from VARCHAR(255)     NOT NULL,
    city_to   VARCHAR(255)     NOT NULL,
    departure TIMESTAMP        NOT NULL,
    arrival   TIMESTAMP        NOT NULL,
    price     DOUBLE PRECISION NOT NULL,
    link      VARCHAR(1000)
);

--changeset jasokolowska:3
CREATE TABLE place
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);

--changeset jasokolowska:4
CREATE TABLE route
(
    id                 BIGSERIAL PRIMARY KEY,
    destination_id     BIGINT,
    origin_id          BIGINT,
    max_price          NUMERIC(10, 2),
    email_notification BOOLEAN NOT NULL,
    CONSTRAINT fk_destination
        FOREIGN KEY (destination_id)
            REFERENCES place (id),
    CONSTRAINT fk_origin
        FOREIGN KEY (origin_id)
            REFERENCES place (id)
);

--changeset jasokolowska:5
ALTER TABLE flight
    ADD COLUMN route_id BIGINT;

--changeset jasokolowska:6
ALTER TABLE flight
    ADD CONSTRAINT fk_route
        FOREIGN KEY (route_id)
            REFERENCES route (id);

--changeset jasokolowska:7
ALTER TABLE airport
    ADD COLUMN place_id BIGINT;

--changeset jasokolowska:8
ALTER TABLE airport
    ADD CONSTRAINT fk_place
        FOREIGN KEY (place_id)
            REFERENCES place (id);

--changeset jasokolowska:9
CREATE TABLE IF NOT EXISTS users
(
    user_id    SERIAL PRIMARY KEY,
    email   VARCHAR(255) NOT NULL UNIQUE,
    pwd   VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

--changeset jasokolowska:10
ALTER TABLE flight
    ALTER COLUMN price TYPE NUMERIC(10, 2);

