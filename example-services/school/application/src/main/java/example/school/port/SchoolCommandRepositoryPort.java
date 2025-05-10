package example.school.port;

import example.school.School;

public interface SchoolCommandRepositoryPort {
    School save(School school);
    
    School updateStudentCnt(Long id);
}
