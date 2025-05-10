package example.school.web;

import example.school.usecase.SchoolCreateUseCase;
import example.school.usecase.SchoolUpdateUseCase;
import example.student.web.dto.SchoolCommandDto.SchoolUpdateResponse;
import example.student.web.dto.SchoolCommandDto.SchoolCreateCommand;
import example.student.web.dto.SchoolCommandDto.SchoolCreateResponse;
import example.student.web.mapper.SchoolDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolCommandApi {
    private final SchoolCreateUseCase schoolCreateUseCase;
    private final SchoolUpdateUseCase schoolUpdateUseCase;
    private final SchoolDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolCreateResponse create(
            @RequestBody @Valid SchoolCreateCommand requestBody
    ) {
        var school = mapper.toDomain(requestBody);

        return SchoolCreateResponse.builder()
                .school(schoolCreateUseCase.createSchool(school))
                .build();
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolUpdateResponse update(
            @PathVariable Long id
    ) {
        return SchoolUpdateResponse.builder()
                .school(schoolUpdateUseCase.updateSchool(id))
                .build();
    }
}
