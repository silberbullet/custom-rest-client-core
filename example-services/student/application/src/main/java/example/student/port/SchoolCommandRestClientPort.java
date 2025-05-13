package example.student.port;

import example.school.School;

public interface SchoolCommandRestClientPort {
    School updateStudentCnt(Long id);
}
