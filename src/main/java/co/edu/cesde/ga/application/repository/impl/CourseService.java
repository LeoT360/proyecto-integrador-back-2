package co.edu.cesde.ga.application.repository.impl;

import co.edu.cesde.ga.application.repository.CourseRepository;
import co.edu.cesde.ga.domain.exceptions.CourseAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.CourseNotFoundException;
import co.edu.cesde.ga.domain.models.Course;
import co.edu.cesde.ga.infrastructure.repository.CourseJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseRepository {

    private final CourseJpaRepository courseRepository;

    public CourseService(CourseJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (courseRepository.existsById(course.getId())) {
            throw new CourseAlreadyExistsException(course.getId());
        }

        return courseRepository.save(course);
    }

    @Override
    public Boolean existsById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        return courseRepository.existsById(id);
    }

    @Override
    public Course findById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        courseRepository.deleteById(id);
    }

    @Override
    public Course update(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (!courseRepository.existsById(course.getId())) {
            throw new CourseNotFoundException(course.getId());
        }

        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}