-- liquibase formatted sql

-- changeset huyhanhat:1733218703593-1
ALTER TABLE room
    ADD host_id BIGINT NULL;

-- changeset huyhanhat:1733218703593-2
ALTER TABLE room
    ADD password VARCHAR(255) NULL;

-- changeset huyhanhat:1733218703593-3
ALTER TABLE room
    ADD CONSTRAINT FKt8cvvbhhhmwmq90bw9qbnt5uw FOREIGN KEY (host_id) REFERENCES user (id);