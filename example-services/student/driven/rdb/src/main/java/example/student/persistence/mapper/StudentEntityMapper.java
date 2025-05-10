package example.student.persistence.mapper;

import example.student.Student;
import example.student.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {

    Student toDomain(StudentEntity studentEntity);

    StudentEntity toEntity(Student student);
}
