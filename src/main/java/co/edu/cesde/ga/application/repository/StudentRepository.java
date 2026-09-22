package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.application.dto.response.CreateStudentResponseDto;
import co.edu.cesde.ga.domain.models.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {

    Student save(CreateStudentResponseDto student);
    Boolean existsById(Long id);
    Student findById(Long id);
    void deleteById(Long id);
    Student update(Student student);
    List<Student> findAll();
}
