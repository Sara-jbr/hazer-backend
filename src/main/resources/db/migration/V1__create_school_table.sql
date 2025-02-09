CREATE TABLE schools (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schoolName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(255) NOT NULL,
    school_type VARCHAR(255) NOT NULL,
    grade_level VARCHAR(255) NOT NULL,
    owner_id BIGINT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES owners(id) ON DELETE CASCADE
);