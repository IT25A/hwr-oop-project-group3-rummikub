--liquibase formatted sql

--changeset system:1 dbms:postgresql
CREATE TABLE rummikub_spiele
(
    id       UUID PRIMARY KEY,
    game     JSONB NOT NULL
);
