CREATE SCHEMA security;

CREATE SEQUENCE role_seq
  START 1
  INCREMENT 1;

CREATE TABLE security.roles (
  role_id      int8 PRIMARY KEY   NOT NULL DEFAULT nextval('role_seq'),
  authority    CHARACTER VARYING (255) DEFAULT NULL
);

CREATE TABLE security.users (
  user_id      CHARACTER VARYING(64) PRIMARY KEY NOT NULL,
  enabled       int4 ,
  username        CHARACTER VARYING(255) NOT NULL,
  last_name    CHARACTER VARYING(255) NOT NULL,
  name         CHARACTER VARYING(255) NOT NULL,
  password     CHARACTER VARYING(255) NOT NULL,
  created_date TIMESTAMP    NOT NULL
);

CREATE TABLE security.user_roles (
  user_id CHARACTER VARYING(64) NOT NULL REFERENCES security.users (user_id),
  role_id int8               NOT NULL REFERENCES security.roles (role_id)
);