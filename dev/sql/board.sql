create sequence board_sequence start 1 increment 1;

CREATE TABLE IF NOT EXISTS board (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    register VARCHAR(50) NOT NULL,
    contents TEXT,
    upload_time TIMESTAMP,
    location VARCHAR(255),
    room_status VARCHAR(15),
    room_type VARCHAR(15),
    del_type BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE INDEX idx_board_id ON board(id);

CREATE TABLE IF NOT EXISTS comment (
    id SERIAL PRIMARY KEY,
    board_id INT NOT NULL,
    content TEXT,
    commenter VARCHAR(50) NOT NULL,
    del_type BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE INDEX idx_comment_id ON comment(id);

ALTER TABLE comment
ADD CONSTRAINT fk_comment_board
FOREIGN KEY (board_id)
REFERENCES board(id);