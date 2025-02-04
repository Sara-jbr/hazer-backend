CREATE TABLE school_admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    createdAt VARCHAR(255),
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owner(id)
);