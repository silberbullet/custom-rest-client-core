package example.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;

    private Long schoolId;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;
}
