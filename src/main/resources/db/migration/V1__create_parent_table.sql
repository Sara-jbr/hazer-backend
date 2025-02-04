CREATE TABLE parent (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    address VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    mobileNumber VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);