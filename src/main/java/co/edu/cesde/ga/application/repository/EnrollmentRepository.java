package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.domain.models.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository {

    Enrollment save(Enrollment enrollment);
    Boolean existsById(String id);
    Enrollment findById(String id);
    void deleteById(String id);
    Enrollment update(Enrollment enrollment);
    List<Enrollment> findAll();
}
