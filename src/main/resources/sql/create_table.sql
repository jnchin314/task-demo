CREATE TABLE tasks (

    id SERIAL PRIMARY KEY,

    task_name VARCHAR(50) NOT NULL,

    email VARCHAR(100) UNIQUE

);