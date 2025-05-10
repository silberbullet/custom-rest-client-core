package example.student.port;

import example.student.Student;

public interface StudentCommandRepositoryPort {
    Student save(Student student);
}
