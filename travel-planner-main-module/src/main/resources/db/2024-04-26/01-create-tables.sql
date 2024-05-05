-- liquibase formatted sql

-- changeset jasokolowska:1
CREATE TABLE location
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    latitude       DOUBLE PRECISION,
    longitude      DOUBLE PRECISION,
    geo_api_place_id VARCHAR(255)
);

-- changeset jasokolowska:2
CREATE TABLE airport
(
    id           BIGSERIAL PRIMARY KEY,
    code         VARCHAR(10)  NOT NULL,
    name         VARCHAR(255) NOT NULL,
    location_id  BIGINT,
    CONSTRAINT fk_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
);

-- changeset jasokolowska:3
CREATE TABLE route
(
    id                 BIGSERIAL PRIMARY KEY,
    destination_id     BIGINT,
    origin_id          BIGINT,
    max_price          NUMERIC(10, 2),
    email_notification BOOLEAN NOT NULL
);

-- changeset jasokolowska:4
CREATE TABLE flight
(
    id        BIGSERIAL PRIMARY KEY,
    city_from VARCHAR(255)  NOT NULL,
    city_to   VARCHAR(255)  NOT NULL,
    departure TIMESTAMP    NOT NULL,
    arrival   TIMESTAMP    NOT NULL,
    price     NUMERIC(10, 2) NOT NULL,
    link      VARCHAR(1000),
    route_id  BIGINT,
    CONSTRAINT fk_route
        FOREIGN KEY (route_id)
            REFERENCES route (id)
);

-- changeset jasokolowska:5
CREATE TABLE IF NOT EXISTS users
(
    user_id  SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    pwd      VARCHAR(255) NOT NULL,
    role     VARCHAR(255)
);
