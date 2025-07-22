CREATE TABLE games (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description text,
    rating numeric,
    date_release date
)