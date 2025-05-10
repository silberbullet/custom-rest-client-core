package example.student.web.mapper;

import example.school.School;
import example.student.web.dto.SchoolCommandDto.SchoolCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolDtoMapper {

    School toDomain(SchoolCreateCommand command);
}
