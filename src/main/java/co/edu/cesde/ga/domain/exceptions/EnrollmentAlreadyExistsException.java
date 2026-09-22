package co.edu.cesde.ga.domain.exceptions;

public class EnrollmentAlreadyExistsException extends ResourceAlreadyExistsException {

    public EnrollmentAlreadyExistsException(String id) {
        super("Enrollment with id " + id + " already exists.");
    }
}