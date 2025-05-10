package example.student.web.mapper;

import example.student.Student;
import example.student.web.dto.StudentCommandDto.StudentCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    Student toDomain(StudentCreateCommand command);
}
