package co.edu.cesde.ga.domain.exceptions;

public class EnrollmentAlreadyExistsException extends RuntimeException {

    public EnrollmentAlreadyExistsException(String id) {
        super("Enrollment with id " + id + " already exists.");
    }
}