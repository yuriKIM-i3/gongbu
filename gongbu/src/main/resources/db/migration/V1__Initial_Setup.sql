DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS word_like;

CREATE TABLE users(
	user_id serial PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	user_role VARCHAR NOT NULL,
	delete_flg  INT NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE words(
	word_id serial PRIMARY KEY,
	user_id INT NOT NULL,
	word_name VARCHAR(50) NOT NULL,
	word_pronunciation VARCHAR(100) NOT NULL,
	word_meaning VARCHAR(255) NOT NULL,
	word_example VARCHAR(500),
	word_hits INT,
	word_like INT,
	delete_flg  INT NOT NULL,
	created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE word_like(
	word_like_id serial PRIMARY KEY,
	user_id INT NOT NULL,
	word_id INT NOT NULL,
	created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);