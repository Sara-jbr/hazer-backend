-- Create Table: student_teacher (Many-to-Many)
CREATE TABLE student_teacher (
    student_id BIGINT NOT NULL,
    teacher_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, teacher_id),
    CONSTRAINT fk_student_teacher_student FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    CONSTRAINT fk_student_teacher_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE
);