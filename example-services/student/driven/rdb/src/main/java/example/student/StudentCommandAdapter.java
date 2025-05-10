package example.student;

import example.student.persistence.mapper.StudentEntityMapper;
import example.student.port.StudentCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentCommandAdapter implements StudentCommandRepositoryPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Student save(Student Student) {
        var StudentEntity = studentEntityMapper.toEntity(Student);
        try {
            var newStudent = studentJpaRepository.save(StudentEntity);
            studentJpaRepository.flush();
            return studentEntityMapper.toDomain(newStudent);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}