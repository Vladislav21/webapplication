CREATE TABLE users (
  id                     SERIAL PRIMARY KEY,
  loggin                 CHARACTER VARYING,
  password               CHARACTER VARYING,
  number_average_attemps INTEGER DEFAULT 0
);

CREATE TABLE games (
  id       SERIAL PRIMARY KEY,
  attempts INTEGER
);

CREATE TABLE game_logs (
  id      SERIAL PRIMARY KEY,
  id_user INTEGER,
  data    CHARACTER VARYING,
  game_id INTEGER,
  CONSTRAINT fk_game_to_users FOREIGN KEY (id_user)
  REFERENCES users (id)
);

