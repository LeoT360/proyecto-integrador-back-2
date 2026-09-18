package co.edu.cesde.ga.application.repository;

import co.edu.cesde.ga.domain.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository {

    Course save(Course course);
    Boolean existsById(Long id);
    Course findById(Long id);
    void deleteById(Long id);
    Course update(Course course);
    List<Course> findAll();
}
