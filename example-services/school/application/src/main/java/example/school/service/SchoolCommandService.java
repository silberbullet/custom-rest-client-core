package example.school.service;

import example.school.School;
import example.school.port.SchoolCommandRepositoryPort;
import example.school.usecase.SchoolUpdateUseCase;
import lombok.RequiredArgsConstructor;
import example.school.usecase.SchoolCreateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolCommandService implements SchoolCreateUseCase, SchoolUpdateUseCase {
    
    private final SchoolCommandRepositoryPort schoolCommandRepositoryPort;
    
    @Override
    public School createSchool(School school) {
        return schoolCommandRepositoryPort.save(school);
    }
    
    @Override
    public School updateSchool(Long id) {
        return schoolCommandRepositoryPort.updateStudentCnt(id);
    }
}
