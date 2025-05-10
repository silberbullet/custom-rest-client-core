package example.student.web;

import example.student.usecase.StudentCreateUseCase;
import example.student.web.dto.StudentCommandDto.StudentCreateCommand;
import example.student.web.dto.StudentCommandDto.StudentCreateResponse;
import example.student.web.mapper.StudentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentCommandApi {
    private final StudentCreateUseCase studentCreateUseCase;
    private final StudentDtoMapper mapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreateResponse create(@RequestBody @Valid StudentCreateCommand requestBody) {
        var student = mapper.toDomain(requestBody);
        
        return StudentCreateResponse.builder()
                .student(studentCreateUseCase.createStudent(student))
                .build();
    }
}
