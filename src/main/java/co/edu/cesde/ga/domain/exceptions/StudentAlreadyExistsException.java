package co.edu.cesde.ga.domain.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException(Long id) {
        super("Student with id " + id + " already exists.");
    }
}
