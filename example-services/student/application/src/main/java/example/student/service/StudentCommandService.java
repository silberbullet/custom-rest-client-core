package example.student.service;

import example.student.Student;
import example.student.port.StudentCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import example.student.usecase.StudentCreateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentCommandService implements StudentCreateUseCase {
    
    private final StudentCommandRepositoryPort StudentCommandRepositoryPort;
    
    @Override
    public Student createStudent(Student student) {
        return StudentCommandRepositoryPort.save(student);
    }
}
