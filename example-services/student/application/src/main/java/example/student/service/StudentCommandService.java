package example.student.service;

import example.student.Student;
import example.student.port.SchoolCommandRestClientPort;
import example.student.port.StudentCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import example.student.usecase.StudentCreateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentCommandService implements StudentCreateUseCase {
    
    private final StudentCommandRepositoryPort StudentCommandRepositoryPort;
    //  ❌ 해결방안 1
    // private final SchoolCommandRepositoryPort schoolCommandRepositoryPort;
    // 🛎️ 해결방안 2
    private final SchoolCommandRestClientPort schoolCommandRestClientPort;

    /*
       🚨 학생 등록 시, 해당 학교에 전첸 학생 수를 증가시키는 요구사항 추가
    */
    @Override
    public Student createStudent(Student student) {
        var newStudent =  StudentCommandRepositoryPort.save(student);

        // 학교 총 학생 수 업데이트 로직 호출
        //  ❌ 해결방안 1
        // schoolCommandRepositoryPort.updateStudentCnt(newStudent.getSchoolId());
        // 🛎️ 해결방안 2
        schoolCommandRestClientPort.updateStudentCnt(newStudent.getSchoolId());

        return newStudent;
    }
}
