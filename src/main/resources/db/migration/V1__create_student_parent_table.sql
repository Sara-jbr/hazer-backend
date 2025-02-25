-- Create Table: student_parent (Many-to-Many)
CREATE TABLE student_parent (
    student_id BIGINT NOT NULL,
    parent_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, parent_id),
    CONSTRAINT fk_student_parent_student FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    CONSTRAINT fk_student_parent_parent FOREIGN KEY (parent_id) REFERENCES parent(id) ON DELETE CASCADE
);