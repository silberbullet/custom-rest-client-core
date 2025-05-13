package example.school.web.dto;

import example.school.School;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public final class SchoolCommandDto {

    private SchoolCommandDto() {
    }

    @Builder
    public record SchoolCreateCommand(
            @NotBlank(message = "이름을 입력하십시오.")
            String name,
            Long studentCnt
    ) {
    }

    @Builder
    public record SchoolCreateResponse(
            School school
    ) {
    }
    
    @Builder
    public record SchoolUpdateResponse(
            School school
    ) {
    }
}
