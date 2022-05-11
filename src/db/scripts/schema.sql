CREATE TABLE account (
id serial PRIMARY KEY,
login VARCHAR(256) UNIQUE,
password VARCHAR(256),
site VARCHAR(256),
);