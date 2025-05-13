package example.school.web.mapper;

import example.school.School;
import example.school.web.dto.SchoolCommandDto.SchoolCreateCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolDtoMapper {

    School toDomain(SchoolCreateCommand command);
}
