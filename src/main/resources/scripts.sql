CREATE TABLE prison
(
    id      BIGSERIAL PRIMARY KEY,
    title   VARCHAR(128)
);

DROP TABLE prison CASCADE;
-- DROP TABLE prisoner;

CREATE TABLE prisoner
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(128),
    prison_id BIGINT NOT NULL REFERENCES prison (id)
);

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(128) UNIQUE,
    name     VARCHAR(128),
    password VARCHAR(128) NOT NULL
);

CREATE TABLE logs_prison
(
    user_id   BIGINT NOT NULL REFERENCES users (id),
    prison_id BIGINT NOT NULL REFERENCES prison (id),
    action    VARCHAR(32) NOT NULL,
    datetime  TIMESTAMP DEFAULT now() NOT NULL
);