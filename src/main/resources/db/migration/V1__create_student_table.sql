CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    student_no VARCHAR(50) NOT NULL,
    grade VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NULL,
    modified_at TIMESTAMP NULL
);