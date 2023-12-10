create sequence account_sequence start 1 increment 1;

 CREATE TABLE account (
    id BIGINT DEFAULT nextval('account_sequence') PRIMARY KEY,
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
    del_type BOOLEAN DEFAULT FALSE,
    auth VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_update_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_at_trigger
BEFORE UPDATE ON account
FOR EACH ROW EXECUTE FUNCTION update_update_at();
