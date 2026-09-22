package co.edu.cesde.ga.domain.exceptions;

public class StudentNotFoundException extends ResourceNotFoundException {

    public StudentNotFoundException(Long id) {
        super("Student with id " + id + " not found.");
    }
}