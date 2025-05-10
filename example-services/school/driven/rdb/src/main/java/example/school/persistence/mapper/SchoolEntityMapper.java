package example.school.persistence.mapper;

import example.school.School;
import example.school.entity.SchoolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolEntityMapper {

    School toDomain(SchoolEntity schoolEntity);

    SchoolEntity toEntity(School school);
}
