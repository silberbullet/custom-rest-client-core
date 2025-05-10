package example.school;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {

    private Long id;

    private String name;

    private Long studentCnt;

    private Instant createdAt;

    private Instant updatedAt;

    @Builder(
        builderClassName = "updateSchoolBuilder",
        builderMethodName = "prepareUpdate",
        buildMethodName = "update"
    )
    public void update(String name, Long studentCnt) {
        Objects.requireNonNull(name, "Title cannot be null");
        Objects.requireNonNull(studentCnt, "content cannot be null");

        this.name = name;
        this.studentCnt = studentCnt;
        this.updatedAt = Instant.now();
    }
}
