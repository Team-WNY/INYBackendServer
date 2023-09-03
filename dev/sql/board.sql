create sequence board_sequence start 1 increment 1;

CREATE TABLE IF NOT EXISTS board (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    register VARCHAR(50) not null,
    contents TEXT,
    upload_time TIMESTAMP,
    location VARCHAR(255),
    room_status VARCHAR(15),
    room_type VARCHAR(15),
    del_type VARCHAR(2),
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE INDEX idx_board_id ON board(id);