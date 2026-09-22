package co.edu.cesde.ga.domain.exceptions;

public class StudentAlreadyExistsException extends ResourceAlreadyExistsException {

    public StudentAlreadyExistsException(Long id) {
        super("Student with id " + id + " already exists.");
    }
}
