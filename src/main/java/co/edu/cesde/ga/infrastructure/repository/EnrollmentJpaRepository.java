package co.edu.cesde.ga.infrastructure.repository;

import co.edu.cesde.ga.domain.models.Course;
import co.edu.cesde.ga.domain.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, String> {

    Enrollment deleteById(Enrollment enrollment);
}
