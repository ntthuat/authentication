CREATE TABLE access_token (
	id bigserial NOT NULL,
	access_token text NOT NULL,
	refresh_token text NULL,
	request_date timestamp NULL,
	expiration boolean NOT NULL,
	created_by varchar(50) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(50) NOT NULL,
	updated_date timestamp NOT NULL,
	PRIMARY KEY (id)
)
;

ALTER Table access_token ALTER created_date TYPE TIMESTAMP WITH TIME ZONE
;
ALTER Table access_token ALTER updated_date TYPE TIMESTAMP WITH TIME ZONE
;
