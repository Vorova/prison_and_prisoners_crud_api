CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(128) UNIQUE,
    name     VARCHAR(128),
    password VARCHAR(128) NOT NULL
);

CREATE TABLE prison
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128)
);

CREATE TABLE prisoner
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(128),
    prison_id  BIGINT NOT NULL REFERENCES prison (id),
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE logs
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT                  NOT NULL REFERENCES users (id),
    subject_id BIGINT                  NOT NULL,
    action     VARCHAR(32)             NOT NULL,
    datetime   TIMESTAMP DEFAULT now() NOT NULL
);

-- DROP TABLE public.prison CASCADE;
-- DROP TABLE public.prisoner CASCADE;
-- DROP TABLE public.logs CASCADE;