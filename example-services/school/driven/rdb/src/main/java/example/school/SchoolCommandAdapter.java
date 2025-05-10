package example.school;

import example.school.persistence.mapper.SchoolEntityMapper;
import example.school.port.SchoolCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SchoolCommandAdapter implements SchoolCommandRepositoryPort {

    private final SchoolJpaRepository schoolJpaRepository;
    private final SchoolEntityMapper schoolEntityMapper;

    @Override
    public School save(School school) {
        var schoolEntity = schoolEntityMapper.toEntity(school);
        try {
            var newSchool = schoolJpaRepository.save(schoolEntity);
            schoolJpaRepository.flush();
            return schoolEntityMapper.toDomain(newSchool);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public School updateStudentCnt(Long id) {
        var schoolEntity = schoolJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        
        // 학생 수 +1 증가
        schoolEntity.updateStudentCnt();
        
        schoolJpaRepository.save(schoolEntity);
        
        return schoolEntityMapper.toDomain(schoolEntity);
    }
}