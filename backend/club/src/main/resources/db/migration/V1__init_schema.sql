CREATE TABLE groups (
                        id                 VARCHAR(64) PRIMARY KEY,
                        club_id            VARCHAR(100) NOT NULL,
                        title              VARCHAR(255) NOT NULL,
                        description        TEXT,
                        registration_opens TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE member_types (
                              id      VARCHAR(64) PRIMARY KEY,
                              form_id VARCHAR(64) NOT NULL REFERENCES groups(id),
                              name    VARCHAR(100) NOT NULL
);

CREATE TABLE members (
                         id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         form_id        VARCHAR(64) NOT NULL REFERENCES groups(id),
                         member_type_id VARCHAR(64) NOT NULL REFERENCES member_types(id),
                         first_name     VARCHAR(100) NOT NULL,
                         last_name      VARCHAR(100) NOT NULL,
                         email          VARCHAR(255) NOT NULL,
                         phone          VARCHAR(30),
                         birth_date     DATE NOT NULL,
                         submitted_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);