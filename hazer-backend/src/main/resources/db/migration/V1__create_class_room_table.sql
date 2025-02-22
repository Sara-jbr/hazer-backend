CREATE TABLE classroom (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class_name VARCHAR(255) NOT NULL,
    teacher_id BIGINT NOT NULL,
    created_at TIMESTAMP NULL,
    modified_at TIMESTAMP NULL,
    CONSTRAINT fk_classroom_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE
);