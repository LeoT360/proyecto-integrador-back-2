package co.edu.cesde.ga.domain.exceptions;

public class EnrollmentNotFoundException extends ResourceNotFoundException {

    public EnrollmentNotFoundException(String id) {
        super("Enrollment with id " + id + " not found.");
    }
}