package co.edu.cesde.ga.infrastructure.repository;

import co.edu.cesde.ga.domain.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    Student deleteById(Student student);
}
