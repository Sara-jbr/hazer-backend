CREATE TABLE classroom_student (
    classroom_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (classroom_id, student_id),
    FOREIGN KEY (classroom_id) REFERENCES classrooms (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE
);