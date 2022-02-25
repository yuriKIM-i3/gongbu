CREATE TABLE users(
	user_id serial PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	user_role VARCHAR NOT NULL,
	delete_flg  INT NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
