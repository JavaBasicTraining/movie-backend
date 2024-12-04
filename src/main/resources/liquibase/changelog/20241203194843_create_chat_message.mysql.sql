-- liquibase formatted sql

-- changeset huyhanhat:1733284127699-1
CREATE TABLE chat_message
(
    id                 BINARY(16) NOT NULL,
    created_by         VARCHAR(50) NOT NULL,
    created_date       datetime(6) NULL,
    last_modified_by   VARCHAR(50) NULL,
    last_modified_date datetime(6) NULL,
    content            VARCHAR(255) NULL,
    type               VARCHAR(50) NOT NULL,
    room_id            BINARY(16) NULL,
    sender_id          BIGINT NULL,
    CONSTRAINT chat_messagePK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733284127699-2
ALTER TABLE room
    ADD created_by VARCHAR(50) NOT NULL;

-- changeset huyhanhat:1733284127699-3
ALTER TABLE room
    ADD created_date datetime(6) NULL;

-- changeset huyhanhat:1733284127699-4
ALTER TABLE room
    ADD last_modified_by VARCHAR(50) NULL;

-- changeset huyhanhat:1733284127699-5
ALTER TABLE room
    ADD last_modified_date datetime(6) NULL;

-- changeset huyhanhat:1733284127699-6
ALTER TABLE chat_message
    ADD CONSTRAINT FKm92rh2bmfw19xcn7nj5vrixsi FOREIGN KEY (sender_id) REFERENCES user (id);

-- changeset huyhanhat:1733284127699-7
ALTER TABLE chat_message
    ADD CONSTRAINT FKtc2pputadncp2auve2t53i7pc FOREIGN KEY (room_id) REFERENCES room (id);

