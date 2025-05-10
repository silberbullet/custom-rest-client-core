package example.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Getter
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "school")
public class SchoolEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Long studentCnt;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;
    
    @Builder
    public SchoolEntity(String name, Long studentCnt) {
        this.name = name;
        this.studentCnt = studentCnt;
    }
    
    public void updateStudentCnt() {
        this.studentCnt = this.studentCnt + 1;
        this.updatedAt = Instant.now();
    }
}