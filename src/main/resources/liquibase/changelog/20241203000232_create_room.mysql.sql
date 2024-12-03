-- liquibase formatted sql

-- changeset huyhanhat:1733212955450-1
CREATE TABLE room
(
    id   BINARY(16) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT roomPK PRIMARY KEY (id)
);