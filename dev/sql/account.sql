 CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    account_id VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50),
    birthdate TIMESTAMP,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    red_heart_count INTEGER,
    black_heart_count INTEGER,
    follower_count INTEGER,
    following_count INTEGER,
    uploaded_ami_count INTEGER,
    auth VARCHAR(10) NOT NULL
);