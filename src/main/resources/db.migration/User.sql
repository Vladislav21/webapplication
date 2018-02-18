CREATE TABLE users (
  id                     SERIAL PRIMARY KEY,
  loggin                 CHARACTER VARYING,
  password               CHARACTER VARYING,
  number_average_attemps INTEGER DEFAULT 0
);


INSERT INTO users (loggin, password) VALUES ('Admin', 'admin'), ('Test', 'test');

CREATE TABLE attempts (
  user_id       INTEGER,
  created_at    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
  number_attemp INTEGER,
  CONSTRAINT fk_attempts_to_users FOREIGN KEY (user_id)
  REFERENCES users (id)
);
