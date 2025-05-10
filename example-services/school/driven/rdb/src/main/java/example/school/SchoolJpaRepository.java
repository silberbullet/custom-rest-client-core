package example.school;

import example.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolJpaRepository extends JpaRepository<SchoolEntity, Long> { }