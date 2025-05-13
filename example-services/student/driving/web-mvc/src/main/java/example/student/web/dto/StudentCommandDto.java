package example.student.web.dto;

import example.student.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public final class StudentCommandDto {

    private StudentCommandDto() {
    }

    @Builder
    public record StudentCreateCommand(
            @NotBlank(message = "이름을 입력하십시오.")
            String name,
            Long schoolId
    ) {
    }

    @Builder
    public record StudentCreateResponse(
            Student student
    ) {
    }
}
