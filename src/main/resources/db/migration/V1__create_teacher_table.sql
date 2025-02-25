CREATE TABLE teacher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    field VARCHAR(255),
    created_at TIMESTAMP NULL,
    modified_at TIMESTAMP NULL
);