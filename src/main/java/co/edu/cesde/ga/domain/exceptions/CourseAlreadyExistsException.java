package co.edu.cesde.ga.domain.exceptions;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException(Long id) {
        super("Course with id " + id + " already exists.");
    }
}