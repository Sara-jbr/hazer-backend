CREATE TABLE student_parent (
    student_id BIGINT NOT NULL,
    parent_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, parent_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (parent_id) REFERENCES parent(id)
);