CREATE TABLE student_classroom (
    student_id BIGINT NOT NULL,
    classroom_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, classroom_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);