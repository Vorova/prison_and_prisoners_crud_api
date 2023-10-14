CREATE TABLE prison
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128)
);

CREATE TABLE prisoner
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128),
    prison_id BIGINT REFERENCES prison (id)
);