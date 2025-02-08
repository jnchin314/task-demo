CREATE DATABASE demos;
GRANT ALL PRIVILEGES ON DATABASE demos TO postgres;

\c demos;

CREATE SCHEMA todo;

CREATE TYPE status_enum AS ENUM ('created', 'in_progress', 'done');

CREATE TABLE todo.tasks (

    id SERIAL PRIMARY KEY,

    task_name VARCHAR(50) NOT NULL,

    description VARCHAR(255) NOT NULL,

    created_at timestamp default now(),

    updated_at timestamp default now(),

    status status_enum default 'created'

);