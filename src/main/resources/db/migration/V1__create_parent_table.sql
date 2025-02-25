CREATE TABLE parent (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    mobile_number VARCHAR(20),
    created_at TIMESTAMP NULL,
    modified_at TIMESTAMP NULL
);