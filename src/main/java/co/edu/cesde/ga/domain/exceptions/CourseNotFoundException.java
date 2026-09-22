package co.edu.cesde.ga.domain.exceptions;

public class CourseNotFoundException extends ResourceNotFoundException {

    public CourseNotFoundException(Long id) {
        super("Course with id " + id + " not found.");
    }
}