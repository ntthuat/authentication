CREATE SEQUENCE hibernate_sequence
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

/* Create Tables */

CREATE TABLE users
(
  user_id bigserial NOT NULL,
  user_name varchar(64) NOT NULL,
  password_ varchar(64) NOT NULL,
  email varchar(254) NOT NULL,
  first_name varchar(64) NULL,
  last_name varchar(64) NULL,
  token_expired bool NULL DEFAULT false,
  active boolean NOT NULL DEFAULT true,
  sso_id varchar(20) NULL,
  created_by varchar(50) NOT NULL,
  created_date timestamp NOT NULL,
  updated_by varchar(50) NOT NULL,
  updated_date timestamp NOT NULL,

  PRIMARY KEY (user_id),
  UNIQUE (user_name),
  UNIQUE (email)
)
;

CREATE TABLE roles
(
  role_id bigserial NOT NULL,
  role_name varchar(20) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date timestamp NOT NULL,
  updated_by varchar(50) NOT NULL,
  updated_date timestamp NOT NULL,

  PRIMARY KEY (role_id)
)
;

CREATE TABLE users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	PRIMARY KEY (user_id, role_id)
)
;